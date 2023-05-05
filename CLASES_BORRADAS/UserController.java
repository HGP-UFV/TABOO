package com.TABOO.Backend;

import com.TABOO.Backend.payload.ApiResponse;
import com.TABOO.Backend.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Nombre de usuario ya en uso"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email ya en uso"), HttpStatus.BAD_REQUEST);
        }

        // Creación del usuario
        Usuario user = new Usuario(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        // Devolución de la respuesta
        return ResponseEntity.ok(new ApiResponse(true, "Usuario registrado correctamente"));
    }
}
