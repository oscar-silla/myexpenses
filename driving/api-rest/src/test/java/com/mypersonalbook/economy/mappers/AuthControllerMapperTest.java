package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.auth.LoginRequestBodyTypeMock.LOGIN_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.auth.ResendVerificationCodeRequestBodyTypeMock.RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerMapperTest {
  AuthControllerMapper authControllerMapper;

  @BeforeEach
  void setUp() {
    this.authControllerMapper = new AuthControllerMapperImpl();
  }

  @Test
  @DisplayName("Should map to user from login request body type")
  void shouldMapToUserFromLoginRequestBodyType() {
    final User RESULT = this.authControllerMapper.toUser(LOGIN_REQUEST_BODY_TYPE());
    assertEquals(USER_EMAIL, RESULT.getEmail());
    assertEquals(USER_PASSWORD, RESULT.getPassword());
    assertNull(RESULT.getId());
    assertNull(RESULT.getName());
    assertNull(RESULT.getRole());
    assertNull(RESULT.getFirstSurname());
    assertNull(RESULT.getSecondSurname());
    assertFalse(RESULT.getVerified());
  }

  @Test
  @DisplayName("Should map to user from resend verification code request body type")
  void shouldMapToUserFromResendVerificationCodeRequestBodyType() {
    final User RESULT =
        this.authControllerMapper.toUser(RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE());
    assertEquals(USER_EMAIL, RESULT.getEmail());
    assertNull(RESULT.getId());
    assertNull(RESULT.getName());
    assertNull(RESULT.getRole());
    assertNull(RESULT.getFirstSurname());
    assertNull(RESULT.getSecondSurname());
    assertNull(RESULT.getPassword());
    assertFalse(RESULT.getVerified());
  }
}
