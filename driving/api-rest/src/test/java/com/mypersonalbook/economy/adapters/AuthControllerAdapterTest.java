package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.auth.LoginUseCasePort;
import com.mypersonalbook.economy.application.ports.driving.auth.ResendVerificationCodeUseCasePort;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.AuthControllerMapper;
import openapi.economy.model.LoginRequestBodyType;
import openapi.economy.model.LoginResponseType;
import openapi.economy.model.ResendVerificationCodeRequestBodyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mypersonalbook.economy.utils.mocks.auth.LoginRequestBodyTypeMock.LOGIN_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.auth.LoginResponseTypeMock.LOGIN_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.auth.ResendVerificationCodeRequestBodyTypeMock.RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.TOKEN;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerAdapterTest {
  AuthControllerAdapter authControllerAdapter;

  @Mock private AuthControllerMapper authControllerMapper;
  @Mock private LoginUseCasePort loginUseCase;
  @Mock private ResendVerificationCodeUseCasePort resendVerificationCodeUseCase;

  @BeforeEach
  void setUp() {
    this.authControllerAdapter =
        new AuthControllerAdapter(
            this.authControllerMapper, this.loginUseCase, this.resendVerificationCodeUseCase);
  }

  @Test
  @DisplayName("Should return 200 status code when post login")
  void shouldReturn200StatusCode_WhenPostLogin() {
    when(this.authControllerMapper.toUser(any(LoginRequestBodyType.class))).thenReturn(USER);
    when(this.loginUseCase.execute(any(User.class))).thenReturn(TOKEN);
    when(this.authControllerMapper.toLoginResponseType(anyString()))
        .thenReturn(LOGIN_RESPONSE_TYPE());
    final ResponseEntity<LoginResponseType> RESULT =
        this.authControllerAdapter.postLogin(LOGIN_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when resend verification code")
  void shouldReturn200StatusCode_WhenResendVerificationCode() {
    when(this.authControllerMapper.toUser(any(ResendVerificationCodeRequestBodyType.class)))
        .thenReturn(USER);
    doNothing().when(this.resendVerificationCodeUseCase).execute(anyString());
    final ResponseEntity<Void> RESULT =
        this.authControllerAdapter.resendVerificationCode(
            RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }
}
