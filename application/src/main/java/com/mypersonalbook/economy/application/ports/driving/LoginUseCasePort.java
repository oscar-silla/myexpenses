package com.mypersonalbook.economy.application.ports.driving;

import com.mypersonalbook.economy.domain.User;

public interface LoginUseCasePort {
  String execute(User user);
}
