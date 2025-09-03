package com.inboop.backend.InboopEntities;
import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;           // email or username

    @Column(nullable = false)
    private String password;           // BCrypt hash

    @Column(nullable = false)
    private String role = "ROLE_USER"; // e.g. "ROLE_USER,ROLE_ADMIN"


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String roles) { this.role = roles; }

}
