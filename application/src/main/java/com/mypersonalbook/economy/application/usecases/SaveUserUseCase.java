package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.ports.driving.SaveUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.domain.Email;
import com.mypersonalbook.economy.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveUserUseCase implements SaveUserUseCasePort {
  private final EmailService emailService;
  private final UserService userService;

  public SaveUserUseCase(EmailService emailService, UserService userService) {
    this.emailService = emailService;
    this.userService = userService;
  }

  @Override
  @Transactional
  public void execute(User user) {
    this.validate(user);
    this.userService.save(user);
    this.emailService.sendEmail(this.buildEmail(user.getEmail()));
  }

  private void validate(User user) {
    if (!user.hasName()
        || !user.hasFirstSurname()
        || !this.emailService.validate(user.getEmail())
        || !user.hasPassword()) {
      throw new BadRequestException();
    }
  }

  private Email buildEmail(String userEmail) {
    Email email = new Email();
    email.setFrom("noreply@gmail.com");
    email.setTo(userEmail);
    email.setSubject("Validation code");
    email.setText("1234");
    return email;
  }
}
