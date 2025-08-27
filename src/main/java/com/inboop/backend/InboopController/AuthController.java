package com.inboop.backend.InboopController;

import com.inboop.backend.InboopDtos.RequestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {
    @Autowired
    private InMemoryUserDetailsManager userStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // simple HTML form
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RequestRegister request, Model model) {
        String username = request.getUsername();
        String rawPassword = request.getPassword();

        // Validate input
        if (username == null || username.isBlank() || rawPassword == null || rawPassword.isBlank()) {
            model.addAttribute("error", "Username and password are required.");
            return "register";
        }

        // Check if user already exists
        if (userStore.userExists(username)) {
            model.addAttribute("error", "User already exists.");
            return "register";
        }

        // Create new in-memory user
        userStore.createUser(
                User.withUsername(username)
                        .password(passwordEncoder.encode(rawPassword))
                        .roles("USER")
                        .build()
        );

        // After signup, redirect to home (login)
        model.addAttribute("success", "Registration successful! Please log in.");
        return "home"; // your home.html template
    }

}
