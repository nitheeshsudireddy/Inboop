package com.inboop.backend.InboopConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        // Hardcoded admin
        InMemoryUserDetailsManager admin = new InMemoryUserDetailsManager(
                User.withUsername("admin1")
                        .password(passwordEncoder.encode("password123"))
                        .roles("ADMIN")
                        .build()
        );

        return admin;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/register", "/register-submit").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")                        // home.html is login page
                        .defaultSuccessUrl("/dashboard", true) // always redirect to dashboard
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")                        // same home page used for OAuth2 login
                        .defaultSuccessUrl("/dashboard", true) // after Google/GitHub login → dashboard
                )
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());                  // Default Spring login page is fine

        return http.build();
    }
}
