package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.ports.driving.user.ActivateUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.domain.EmailCode;
import org.springframework.stereotype.Service;

@Service
public class ActivateUserUseCase implements ActivateUserUseCasePort {
  private final EmailService emailService;

  public ActivateUserUseCase(EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  public void execute(EmailCode emailCode) {
    this.emailService.validate(emailCode.getEmail());
    this.emailService.verifyEmailCode(emailCode);
  }
}
