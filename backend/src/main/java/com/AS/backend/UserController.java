package com.AS.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Obtener información de un usuario concreto por su usuario
    @GetMapping("/users/username/{username}")
    public ResponseEntity<String> getUserByName(@PathVariable String username){
        try {
            User user = userRepository.findByUsername(username);
            if (user == null){
                return ResponseEntity.notFound().build();
            }
            else {
                String userJson = "{\"id\": \"" + user.getId() + "\", \"username\": \"" + user.getUsername() + "\", \"email\": \"" + user.getEmail() + "\", \"name\": \"" + user.getName() + "\"}";
                return ResponseEntity.ok(userJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //Obtener todos los usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //Registrar un nuevo usuario
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userRepository.save(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //Para poder hacer Login una persona registrada
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userRepository.findByUsername(loginRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos");
            } else if (!user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos");
            } else {
                String userJson = "{\"id\": \"" + user.getId() + "\", \"username\": \"" + user.getUsername() + "\", \"email\": \"" + user.getEmail() + "\", \"name\": \"" + user.getName() + "\"}";
                return ResponseEntity.ok(userJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
