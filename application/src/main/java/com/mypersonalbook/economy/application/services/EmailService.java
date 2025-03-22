package com.mypersonalbook.economy.application.services;

import static com.mypersonalbook.economy.utils.AppConstants.EMAIL_REGEX;

import com.mypersonalbook.economy.domain.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private final JavaMailSender emailSender;

  public EmailService(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  public boolean validate(String email) {
    return email != null && email.matches(EMAIL_REGEX);
  }

  public boolean sendEmail(Email email) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(email.getFrom());
      message.setTo(email.getTo());
      message.setSubject(email.getSubject());
      message.setText(email.getText());
      this.emailSender.send(message);
      return true;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
