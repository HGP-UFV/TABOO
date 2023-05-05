package com.TABOO.Backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        // aquí puedes manejar la lógica de autenticación
        return "redirect:/"; // redirige al usuario a la página principal después de iniciar sesión correctamente
    }
}
