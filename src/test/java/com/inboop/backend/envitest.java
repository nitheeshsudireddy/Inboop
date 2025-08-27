package com.inboop.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class envitest {
    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    @Test
    public void test() {
        System.out.println("Google Client ID from environment: " + googleClientId);
    }
}



