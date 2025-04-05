package com.mypersonalbook.economy.usecases;

import static com.mypersonalbook.economy.utils.test.mocks.user.EmailCodeMock.EMAIL_CODE;
import static com.mypersonalbook.economy.utils.test.mocks.user.EmailCodeMock.EMAIL_CODE_WITH_1_PLUS_MINUTE;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.exceptions.TooManyRequestsException;
import com.mypersonalbook.economy.application.ports.driving.user.SaveUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.application.usecases.SaveUserUseCase;
import com.mypersonalbook.economy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SaveUserUseCaseTest {
  SaveUserUseCasePort saveUserUseCase;

  @Mock private EmailService emailService;
  @Mock private UserService userService;

  @BeforeEach
  void setUp() {
    this.saveUserUseCase = new SaveUserUseCase(this.emailService, this.userService);
  }

  @Test
  @DisplayName("Should throw bad request exception when validate user name")
  void shouldThrowBadRequestException_WhenValidateUserName() {
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER_WITHOUT_NAME);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when validate user first surname")
  void shouldThrowBadRequestException_WhenValidateUserFirstSurname() {
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER_WITHOUT_FIRST_SURNAME);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when validate user email")
  void shouldThrowBadRequestException_WhenValidateUserEmail() {
    when(this.emailService.validate(any())).thenReturn(false);
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER_WITHOUT_EMAIL);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when validate user password")
  void shouldThrowBadRequestException_WhenValidateUserPassword() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER_WITHOUT_PASSWORD);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should save user when execute and is new user")
  void shouldSaveUser_WhenExecute_AndIsNewUser() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.userService.findByEmail(anyString())).thenReturn(Optional.empty());
    doNothing().when(this.userService).save(any(User.class));
    this.saveUserUseCase.execute(USER);
    verify(this.userService).save(any(User.class));
  }

  @Test
  @DisplayName("Should save user when execute and is not new user but is not verified")
  void shouldSaveUser_WhenExecute_AndIsNotNewUser_ButIsNotVerified() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.userService.findByEmail(anyString())).thenReturn(Optional.of(USER_NOT_VERIFIED));
    doNothing().when(this.userService).save(any(User.class));
    this.saveUserUseCase.execute(USER);
    verify(this.userService).save(any(User.class));
  }

  @Test
  @DisplayName("Should throw conflict exception when find by email and check if user is verified")
  void shouldThrowConflictException_WhenFindByEmail_AndCheckIfUserIsVerified() {
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER);
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.userService.findByEmail(anyString())).thenReturn(Optional.of(USER));
    assertThrows(ConflictException.class, EXECUTABLE);
  }

  @Test
  @DisplayName(
      "Should throw too many requests exception when find email code by email sent before five minutes")
  void shouldThrowTooManyRequestsException_WhenFindEmailCodeByEmailSent_BeforeFiveMinutes() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    when(this.userService.findByEmail(anyString())).thenReturn(Optional.of(USER_NOT_VERIFIED));
    when(this.emailService.findByEmail(anyString()))
        .thenReturn(Optional.of(EMAIL_CODE_WITH_1_PLUS_MINUTE));
    final Executable EXECUTABLE = () -> this.saveUserUseCase.execute(USER_NOT_VERIFIED);
    assertThrows(TooManyRequestsException.class, EXECUTABLE);
  }
}
