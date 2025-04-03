package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "VerificationEmailMO")
@Table(name = "t_verification_email")
public class VerificationEmailMO {
  @Id
  @Column(name = "email", updatable = false)
  private String email;

  @Column(name = "code", nullable = false)
  private UUID code;

  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;

  public VerificationEmailMO() {}

  public VerificationEmailMO(String email, UUID code, LocalDateTime creationDate) {
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

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }
}
