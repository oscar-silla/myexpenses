package com.mypersonalbook.economy.application.usecases.auth;

import com.mypersonalbook.economy.application.ports.driving.auth.ResendVerificationCodeUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.domain.Email;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResendVerificationCodeUseCase implements ResendVerificationCodeUseCasePort {
  private final EmailService emailService;

  public ResendVerificationCodeUseCase(final EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  public void execute(String email) {
    this.emailService.sendVerificationEmail(this.buildEmail(email));
  }

  private Email buildEmail(String userEmail) {
    UUID randomUUID = UUID.randomUUID();
    Email email = new Email();
    email.setFrom("noreply@gmail.com");
    email.setTo(userEmail);
    email.setSubject("Código de verificación");
    email.setText("Su código de verificación es " + randomUUID);
    email.setCode(randomUUID);
    return email;
  }
}
