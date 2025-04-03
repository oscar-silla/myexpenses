package com.mypersonalbook.economy.application.services;

import static com.mypersonalbook.economy.utils.AppConstants.EMAIL_REGEX;

import com.mypersonalbook.economy.application.ports.driven.VerificationEmailRepositoryPort;
import com.mypersonalbook.economy.domain.Email;
import com.mypersonalbook.economy.domain.EmailCode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
  private final JavaMailSender emailSender;
  private final VerificationEmailRepositoryPort verificationEmailRepository;

  public EmailService(
      JavaMailSender emailSender, VerificationEmailRepositoryPort verificationEmailRepository) {
    this.emailSender = emailSender;
    this.verificationEmailRepository = verificationEmailRepository;
  }

  public boolean validate(String email) {
    return email != null && email.matches(EMAIL_REGEX);
  }

  public boolean sendVerificationEmail(Email email) {
    this.sendEmail(email);
    return this.verificationEmailRepository.save(
        email.getTo(), email.getCode(), LocalDateTime.now());
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

  public boolean verifyEmailCode(EmailCode emailCode) {
    return this.verificationEmailRepository
        .findByEmail(emailCode.getEmail())
        .map(_emailVerification -> emailCode.isMatchingCode(_emailVerification.getCode()))
        .orElse(false);
  }
}
