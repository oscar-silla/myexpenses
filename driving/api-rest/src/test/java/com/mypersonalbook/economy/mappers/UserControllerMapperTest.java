package com.mypersonalbook.economy.mappers;

import static com.mypersonalbook.economy.utils.mocks.UserRequestBodyTypeMock.USER_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mypersonalbook.economy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserControllerMapperTest {
  UserControllerMapper userControllerMapper;

  @BeforeEach
  void setUp() {
    this.userControllerMapper = new UserControllerMapperImpl();
  }

  @Test
  @DisplayName("Should map to user")
  void shouldMapToUser() {
    final User user = this.userControllerMapper.toUser(USER_REQUEST_BODY_TYPE());
    assertEquals(USER_NAME, user.getName());
    assertEquals(USER_FIRST_SURNAME, user.getFirstSurname());
    assertEquals(USER_SECOND_SURNAME, user.getSecondSurname());
    assertEquals(USER_EMAIL, user.getEmail());
    assertEquals(USER_PASSWORD, user.getPassword());
  }
}
