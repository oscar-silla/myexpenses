package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.application.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    EmailService emailService;

    @BeforeEach
    void setUp() {
        this.emailService = new EmailService();
    }

    @ParameterizedTest
    @CsvSource({
            "test@example.com, true",
            "user.name+tag+sorting@example.com, true",
            "x@example.com, true",
            "user@sub.example.com, true",
            "invalid-email, false",
            "invalid@domain, false",
            "@missinguser.com, false",
            "user@.com, false",
            "user@com, false",
            "null, false"
    })
    void testEmailValidation(String email, boolean expected) {
        if (email.equals("null")) email = null;
        boolean result = emailService.validate(email);
        assertEquals(expected, result, "Email validation failed for: " + email);
    }
}
