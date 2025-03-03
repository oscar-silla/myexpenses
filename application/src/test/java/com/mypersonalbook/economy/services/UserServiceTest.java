package com.mypersonalbook.economy.services;

import static com.mypersonalbook.economy.utils.test.mocks.UserMock.USER_TO_ENCODE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mypersonalbook.economy.application.ports.driven.UserRepositoryPort;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  UserService userService;

  @Mock private UserRepositoryPort userRepository;

  @BeforeEach
  void setUp() {
    this.userService = new UserService(this.userRepository);
  }

  @Test
  @DisplayName("Should save user")
  void shouldSaveUser_WithEncodedPassword() {
    doNothing().when(this.userRepository).save(any(User.class));
    this.userService.save(USER_TO_ENCODE);
    verify(this.userRepository).save(any(User.class));
  }
}
