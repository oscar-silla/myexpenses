package com.mypersonalbook.economy.adapters;

import static com.mypersonalbook.economy.utils.mocks.user.ActivateUserRequestBodyTypeMock.ACTIVATE_USER_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.user.UserRequestBodyTypeMock.USER_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.mocks.user.EmailCodeMock.EMAIL_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mypersonalbook.economy.application.ports.driving.user.ActivateUserUseCasePort;
import com.mypersonalbook.economy.application.ports.driving.user.SaveUserUseCasePort;
import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.UserControllerMapper;
import openapi.economy.model.ActivateUserRequestBodyType;
import openapi.economy.model.UserRequestBodyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerAdapterTest {
  UserControllerAdapter userControllerAdapter;

  @Mock private UserControllerMapper userControllerMapper;

  @Mock private SaveUserUseCasePort saveUserUseCase;

  @Mock private ActivateUserUseCasePort activateUserUseCase;

  @BeforeEach
  void setUp() {
    this.userControllerAdapter =
        new UserControllerAdapter(
            this.userControllerMapper, this.saveUserUseCase, this.activateUserUseCase);
  }

  @Test
  @DisplayName("Should return 201 status code when post user")
  void shouldReturn201StatusCode_WhenPostUser() {
    when(this.userControllerMapper.toUser(any(UserRequestBodyType.class))).thenReturn(new User());
    doNothing().when(this.saveUserUseCase).execute(any(User.class));
    final ResponseEntity<Void> RESULT =
        this.userControllerAdapter.postUser(USER_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.CREATED, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when activate user")
  void shouldReturn200StatusCode_WhenActivateUser() {
    when(this.userControllerMapper.toEmailCode(any(ActivateUserRequestBodyType.class)))
        .thenReturn(EMAIL_CODE);
    doNothing().when(this.activateUserUseCase).execute(any(EmailCode.class));
    final ResponseEntity<Void> RESULT =
        this.userControllerAdapter.activateUser(ACTIVATE_USER_REQUEST_BODY_TYPE);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }
}
