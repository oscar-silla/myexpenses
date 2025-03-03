package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driven.UserRepositoryPort;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.UserRepositoryMapper;
import com.mypersonalbook.economy.repositories.UserJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryAdapter implements UserRepositoryPort {
  private final UserJpaRepository userJpaRepository;
  private final UserRepositoryMapper userRepositoryMapper;

  public UserRepositoryAdapter(
      UserJpaRepository userJpaRepository, UserRepositoryMapper userRepositoryMapper) {
    this.userJpaRepository = userJpaRepository;
    this.userRepositoryMapper = userRepositoryMapper;
  }

  @Override
  public void save(User user) {
    this.userJpaRepository.save(this.userRepositoryMapper.toUserMO(user));
  }
}
