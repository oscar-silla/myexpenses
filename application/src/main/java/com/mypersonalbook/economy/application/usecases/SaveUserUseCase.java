package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.ports.driving.user.SaveUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.domain.Email;
import com.mypersonalbook.economy.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SaveUserUseCase implements SaveUserUseCasePort {
  private final UserService userService;
  private final EmailService emailService;

  public SaveUserUseCase(EmailService emailService, UserService userService) {
    this.emailService = emailService;
    this.userService = userService;
  }

  @Override
  @Transactional
  public void execute(User user) {
    this.validate(user);
    this.findByEmailAndThrowConflictException(user.getEmail());
    this.userService.save(user);
    this.emailService.sendVerificationEmail(this.buildEmail(user.getEmail()));
  }

  private void validate(User user) {
    if (!user.hasName()
        || !user.hasFirstSurname()
        || !this.emailService.validate(user.getEmail())
        || !user.hasPassword()) {
      throw new BadRequestException();
    }
  }

  private void findByEmailAndThrowConflictException(String email) {
    this.userService
        .findByEmail(email)
        .ifPresent(
            user -> {
              if (user.isVerified()) throw new ConflictException();
            });
  }

  private Email buildEmail(String userEmail) {
    UUID randomUUID = UUID.randomUUID();
    Email email = new Email();
    email.setFrom("noreply@gmail.com");
    email.setTo(userEmail);
    email.setSubject("Código de verificación");
    email.setText("Su código de verificación es " + UUID.randomUUID());
    email.setCode(randomUUID);
    return email;
  }
}
