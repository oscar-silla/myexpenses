package com.mypersonalbook.economy.application.ports.driven;

import com.mypersonalbook.economy.domain.User;

public interface UserRepositoryPort {
  void save(User user);
}
