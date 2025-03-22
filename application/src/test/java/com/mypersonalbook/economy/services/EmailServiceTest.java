package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.application.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.mypersonalbook.economy.utils.test.mocks.EmailMock.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
  EmailService emailService;

  @Mock private JavaMailSender mailSender;

  @BeforeEach
  void setUp() {
    this.emailService = new EmailService(this.mailSender);
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

  @Test
  @DisplayName("Should return true when send email")
  void shouldReturnTrue_WhenSendEmail() {
    doNothing().when(this.mailSender).send(any(SimpleMailMessage.class));
    this.emailService.sendEmail(EMAIL);
    verify(this.mailSender).send(any(SimpleMailMessage.class));
  }

  @Test
  @DisplayName("Should throw runtime exception when send email")
  void shouldThrowRuntimeException_WhenSendEmail() {
    doThrow(new MailSendException("SMTP error"))
        .when(this.mailSender)
        .send(any(SimpleMailMessage.class));
    assertThrows(RuntimeException.class, () -> emailService.sendEmail(EMAIL));
  }
}
