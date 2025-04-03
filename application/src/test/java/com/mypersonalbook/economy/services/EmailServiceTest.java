package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.application.ports.driven.VerificationEmailRepositoryPort;
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

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.mocks.EmailMock.EMAIL;
import static com.mypersonalbook.economy.utils.test.mocks.user.EmailCodeMock.EMAIL_CODE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
  EmailService emailService;

  @Mock private JavaMailSender mailSender;
  @Mock private VerificationEmailRepositoryPort verificationEmailRepository;

  @BeforeEach
  void setUp() {
    this.emailService = new EmailService(this.mailSender, this.verificationEmailRepository);
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
    final boolean RESULT = this.emailService.sendEmail(EMAIL);
    verify(this.mailSender).send(any(SimpleMailMessage.class));
    assertTrue(RESULT);
  }

  @Test
  @DisplayName("Should throw runtime exception when send email")
  void shouldThrowRuntimeException_WhenSendEmail() {
    doThrow(new MailSendException("SMTP error"))
        .when(this.mailSender)
        .send(any(SimpleMailMessage.class));
    assertThrows(RuntimeException.class, () -> emailService.sendEmail(EMAIL));
  }

  @Test
  @DisplayName("Should return true when send verification email")
  void shouldReturnTrue_WhenSendVerificationEmail() {
    doNothing().when(this.mailSender).send(any(SimpleMailMessage.class));
    when(this.verificationEmailRepository.save(any(), any(), any())).thenReturn(true);
    final boolean RESULT = this.emailService.sendVerificationEmail(EMAIL);
    verify(this.mailSender).send(any(SimpleMailMessage.class));
    verify(this.verificationEmailRepository).save(any(), any(), any());
    assertTrue(RESULT);
  }

  @Test
  @DisplayName("Should return true when verify email code")
  void shouldReturnTrue_WhenVerifyEmailCode() {
    when(this.verificationEmailRepository.findByEmail(anyString()))
        .thenReturn(Optional.of(EMAIL_CODE));
    assertTrue(this.emailService.verifyEmailCode(EMAIL_CODE));
  }

  @Test
  @DisplayName("Should return false when verify email code")
  void shouldReturnFalse_WhenVerifyEmailCode() {
    when(this.verificationEmailRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    assertFalse(this.emailService.verifyEmailCode(EMAIL_CODE));
  }
}
