package com.mypersonalbook.economy.usecases;

import static com.mypersonalbook.economy.utils.test.mocks.UserMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
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
  @DisplayName("Should save user when execute")
  void shouldSaveUser_WhenExecute() {
    when(this.emailService.validate(anyString())).thenReturn(true);
    doNothing().when(this.userService).save(any(User.class));
    this.saveUserUseCase.execute(USER);
    verify(this.userService).save(any(User.class));
  }
}
