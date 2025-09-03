package com.inboop.backend.InboopController;

import com.inboop.backend.InboopDtos.RequestRegister;
import com.inboop.backend.InboopEntities.AppUser;
import com.inboop.backend.InboopRepos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RequestRegister request, Model model) {

        String username = request.getUsername();
        String password = request.getPassword();

        if(username == null || password == null || username.isBlank() || password.isBlank()) {
            model.addAttribute("error", "Username and password are required");
            return "register";
        }

        if(userRepo.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        userRepo.save(user);

        return "redirect:/"; // after signup → home/login

    }

}
