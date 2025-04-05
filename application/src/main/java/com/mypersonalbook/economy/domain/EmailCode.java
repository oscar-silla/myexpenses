package com.mypersonalbook.economy.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailCode {
  private String email;
  private UUID code;
  private LocalDateTime creationDate;

  public EmailCode() {}

  public EmailCode(String email, UUID code, LocalDateTime creationDate) {
    this.email = email;
    this.code = code;
    this.creationDate = creationDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UUID getCode() {
    return code;
  }

  public void setCode(UUID code) {
    this.code = code;
  }

  public boolean isMatchingCode(UUID code) {
    return this.code.equals(code);
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }
}
