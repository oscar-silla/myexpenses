package com.mypersonalbook.economy.application.services;

import static com.mypersonalbook.economy.utils.AppConstants.EMAIL_REGEX;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
  public boolean validate(String email) {
    return email != null && email.matches(EMAIL_REGEX);
  }
}
