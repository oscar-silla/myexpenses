package com.mypersonalbook.economy.application.ports.driving.auth;

public interface ResendVerificationCodeUseCasePort {
  void execute(String email);
}
