package com.inboop.backend.auth.controller;

import com.inboop.backend.auth.dto.RegisterRequest;
import com.inboop.backend.auth.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest request, Model model) {
        try {
            if (request.getUsername() == null || request.getPassword() == null ||
                request.getUsername().isBlank() || request.getPassword().isBlank()) {
                model.addAttribute("error", "Username and password are required");
                return "register";
            }

            authService.registerUser(request);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
