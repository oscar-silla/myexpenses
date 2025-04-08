package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.exceptions.TooManyRequestsException;
import com.mypersonalbook.economy.application.ports.driving.user.SaveUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.domain.Email;
import com.mypersonalbook.economy.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
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
    Optional<User> currentUser = this.findUserByEmailAndThrowIfIsVerified(user.getEmail());
    currentUser.ifPresent(value -> user.setId(value.getId()));
    this.findEmailCodeByEmailAndThrowIfIsTooRecent(user.getEmail());
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

  private Optional<User> findUserByEmailAndThrowIfIsVerified(String email) {
    return this.userService
        .findByEmail(email)
        .map(
            user -> {
              if (user.isVerified()) throw new ConflictException();
              return user;
            });
  }

  private void findEmailCodeByEmailAndThrowIfIsTooRecent(String email) {
    this.emailService
        .findByEmail(email)
        .ifPresent(
            emailCode -> {
              if (LocalDateTime.now().isBefore(emailCode.getCreationDate().plusMinutes(5))) {
                throw new TooManyRequestsException();
              }
            });
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
