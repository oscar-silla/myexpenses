package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.ports.driving.user.ActivateUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.usecases.ActivateUserUseCase;
import com.mypersonalbook.economy.domain.EmailCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.user.EmailCodeMock.EMAIL_CODE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivateUserUseCaseTest {
  ActivateUserUseCasePort activateUserUseCase;

  @Mock private EmailService emailService;

  @BeforeEach
  void setUp() {
    this.activateUserUseCase = new ActivateUserUseCase(this.emailService);
  }

  @Test
  @DisplayName("Should validate and verify email code when execute")
  void shouldValidateAndVerifyEmailCode_WhenExecute() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.emailService.verifyEmailCode(any(EmailCode.class))).thenReturn(true);
    this.activateUserUseCase.execute(EMAIL_CODE);
    verify(this.emailService).verifyEmailCode(any(EmailCode.class));
  }

  @Test
  @DisplayName("Should throw new bad request exception when validate email")
  void shouldThrowNewBadRequestException_WhenValidateEmail() {
    when(this.emailService.validate(anyString())).thenReturn(false);
    Executable executable = () -> this.activateUserUseCase.execute(EMAIL_CODE);
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should throw new bad request exception when verify email code")
  void shouldThrowNewBadRequestException_WhenVerifyEmailCode() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.emailService.verifyEmailCode(any(EmailCode.class))).thenReturn(false);
    Executable executable = () -> this.activateUserUseCase.execute(EMAIL_CODE);
    assertThrows(BadRequestException.class, executable);
  }
}
