package com.mypersonalbook.economy.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "VerificationEmailMO")
@Table(name = "t_verification_email")
public class VerificationEmailMO {
  @Id
  @Column(name = "email")
  private String email;

  @Column(name = "code")
  private UUID code;

  public VerificationEmailMO() {}

  public VerificationEmailMO(String email, UUID code) {
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
}
