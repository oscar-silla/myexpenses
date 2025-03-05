package com.mypersonalbook.economy.application.ports.driven;

import com.mypersonalbook.economy.domain.User;

import java.util.Optional;

public interface UserRepositoryPort {
  void save(User user);
  Optional<User> findByEmail(String email);
}
