package com.TABOO.Backend;

import com.TABOO.Backend.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> saveUser(@RequestBody SignUpRequest signUpRequest) throws Exception {
        final Usuario user = userService.registerNewUser(signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getPassword(), signUpRequest.getPhoneNumber());

        // Devolver usuario registrado
        return ResponseEntity.ok(user);
    }
}
