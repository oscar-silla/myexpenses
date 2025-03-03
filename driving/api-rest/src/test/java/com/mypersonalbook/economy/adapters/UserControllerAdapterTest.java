package com.mypersonalbook.economy.adapters;

import static com.mypersonalbook.economy.utils.mocks.UserRequestBodyTypeMock.USER_REQUEST_BODY_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mypersonalbook.economy.application.ports.driving.SaveUserUseCasePort;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.UserControllerMapper;
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

  @BeforeEach
  void setUp() {
    this.userControllerAdapter =
        new UserControllerAdapter(this.userControllerMapper, this.saveUserUseCase);
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
}
