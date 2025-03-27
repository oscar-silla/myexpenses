package com.mypersonalbook.economy.domain;

import java.util.UUID;

public class EmailCode {
  private String email;
  private UUID code;

  public EmailCode() {}

  public EmailCode(String email, UUID code) {
    this.email = email;
    this.code = code;
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
}
