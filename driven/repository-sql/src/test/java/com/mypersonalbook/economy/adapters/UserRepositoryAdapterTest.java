package com.mypersonalbook.economy.adapters;

import static com.mypersonalbook.economy.utils.test.mocks.UserMOMock.USER_MO;
import static com.mypersonalbook.economy.utils.test.mocks.UserMock.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.UserRepositoryMapper;
import com.mypersonalbook.economy.models.UserMO;
import com.mypersonalbook.economy.repositories.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryAdapterTest {
  UserRepositoryAdapter userRepositoryAdapter;

  @Mock private UserJpaRepository userJpaRepository;

  @Mock private UserRepositoryMapper userRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.userRepositoryAdapter =
        new UserRepositoryAdapter(this.userJpaRepository, this.userRepositoryMapper);
  }

  @Test
  @DisplayName("Should save user")
  void shouldSaveUser() {
    when(this.userRepositoryMapper.toUserMO(any(User.class))).thenReturn(USER_MO);
    when(this.userJpaRepository.save(any(UserMO.class))).thenReturn(USER_MO);
    this.userRepositoryAdapter.save(USER);
    verify(this.userJpaRepository).save(any(UserMO.class));
  }
}
