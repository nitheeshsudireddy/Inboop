package com.inboop.backend.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());

        // Sample data for table (TODO: replace with real data from lead service)
        List<Map<String, String>> leads = List.of(
                Map.of("name", "Sophia Clark", "source", "Facebook", "status", "New",
                       "assigned", "Emily Carter", "lastActivity", "2 days ago", "createdAt", "2023-08-15"),
                Map.of("name", "Liam Walker", "source", "Instagram", "status", "Contacted",
                       "assigned", "David Miller", "lastActivity", "1 day ago", "createdAt", "2023-08-14")
        );
        model.addAttribute("leads", leads);

        return "dashboard";
    }

    @GetMapping("/api/v1/auth/me")
    @ResponseBody
    public Map<String, Object> currentUser(@AuthenticationPrincipal UserDetails user) {
        if (user == null) {
            return Map.of("authenticated", false);
        }
        return Map.of(
                "authenticated", true,
                "username", user.getUsername(),
                "roles", user.getAuthorities()
        );
    }
}
