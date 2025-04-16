package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.ports.driving.LoginUseCasePort;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.domain.User;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase implements LoginUseCasePort {
  private final AuthService authService;

  public LoginUseCase(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public String execute(User user) {
    return this.authService.login(user);
  }
}
