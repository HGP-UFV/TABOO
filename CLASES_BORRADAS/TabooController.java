package com.TABOO.Backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/taboo")
public class TabooController {

    private final String FILE_NAME = "src/main/resources/PRUEBAS_LECTURA_USUARIOS.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = leerUsuariosDesdeJson();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        List<Usuario> usuarios = leerUsuariosDesdeJson();
        Optional<Usuario> optionalUsuario = usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = leerUsuariosDesdeJson();
        Optional<Usuario> optionalUsuario = usuarios.stream().filter(u -> u.getCorreo().equals(usuario.getCorreo())).findFirst();
        if (optionalUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            usuario.setPassword(encoder.encode(usuario.getPassword())); // Encriptamos la contraseña antes de guardarla
            usuarios.add(usuario);
            escribirUsuariosEnJson(usuarios);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        List<Usuario> usuarios = leerUsuariosDesdeJson();
        Optional<Usuario> optionalUsuario = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setUsuario(usuario.getUsuario());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setPassword(encoder.encode(usuario.getPassword())); // Encriptamos la contraseña antes de actualizarla
            usuarioExistente.setnTelefono(usuario.getnTelefono());
            escribirUsuariosEnJson(usuarios);
            return ResponseEntity.ok(usuarioExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        List<Usuario> usuarios = leerUsuariosDesdeJson();
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
        escribirUsuariosEnJson(usuarios);
    }

    private List<Usuario> leerUsuariosDesdeJson() {
        Path ficheroJson = Path.of("src/main/resources/PRUEBAS_LECTURA_USUARIOS.json");
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(ficheroJson)) {
            ObjectMapper objectMapper = new ObjectMapper();
            usuarios = objectMapper.readValue(br, new TypeReference<List<Usuario>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private void escribirUsuariosEnJson(List<Usuario> usuarios) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("usuarios.json"), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
