package com.mypersonalbook.economy.usecases.auth;

import com.mypersonalbook.economy.application.ports.driving.LoginUseCasePort;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.usecases.LoginUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.TOKEN;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {
  LoginUseCasePort loginUseCase;

  @Mock private AuthService authService;

  @BeforeEach
  void setUp() {
    this.loginUseCase = new LoginUseCase(this.authService);
  }

  @Test
  @DisplayName("Should login when execute")
  void shouldLogin_WhenExecute() {
    when(this.authService.login(any())).thenReturn(TOKEN);
    this.loginUseCase.execute(USER);
    verify(this.authService).login(any());
  }
}
