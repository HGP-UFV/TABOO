package com.TABOO.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registerNewUser(String username, String email, String password, String phoneNumber) {
        // Validar datos del formulario
        // ...

        // Crear usuario
        Usuario user = new Usuario(null, "Nombre por defecto", username, email, passwordEncoder.encode(password));

        // Guardar usuario en la base de datos
        return userRepository.save(user);
    }


    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findByUsuario(username);
    }

    public List<Usuario> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Usuario> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(Usuario user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
