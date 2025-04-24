package com.mypersonalbook.economy.usecases.auth;

import com.mypersonalbook.economy.application.ports.driving.auth.ResendVerificationCodeUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.usecases.auth.ResendVerificationCodeUseCase;
import com.mypersonalbook.economy.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResendVerificationCodeUseCaseTest {
  ResendVerificationCodeUseCasePort resendVerificationCodeUseCase;

  @Mock private EmailService emailService;

  @BeforeEach
  void setUp() {
    this.resendVerificationCodeUseCase = new ResendVerificationCodeUseCase(this.emailService);
  }

  @Test
  @DisplayName("Should send verification email when execute")
  void shouldSendVerificationEmail_WhenExecute() {
    when(this.emailService.sendVerificationEmail(any(Email.class))).thenReturn(true);
    this.resendVerificationCodeUseCase.execute(USER_EMAIL);
    verify(this.emailService).sendVerificationEmail(any(Email.class));
  }
}
