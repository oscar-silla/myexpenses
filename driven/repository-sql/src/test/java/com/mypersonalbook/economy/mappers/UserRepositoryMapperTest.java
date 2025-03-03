package com.mypersonalbook.economy.mappers;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.UserMock.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mypersonalbook.economy.models.UserMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryMapperTest {
  UserRepositoryMapper userRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.userRepositoryMapper = new UserRepositoryMapperImpl();
  }

  @Test
  void shouldMapToUser() {
    final UserMO RESULT = this.userRepositoryMapper.toUserMO(USER);
    assertEquals(USER_NAME, RESULT.getName());
    assertEquals(USER_FIRST_SURNAME, RESULT.getFirstSurname());
    assertEquals(USER_SECOND_SURNAME, RESULT.getSecondSurname());
    assertEquals(USER_EMAIL, RESULT.getEmail());
    assertEquals(USER_PASSWORD, RESULT.getPassword());
  }
}
