package com.mypersonalbook.economy.domain;

import com.mypersonalbook.economy.application.enums.Role;
import org.springframework.util.StringUtils;

public class User {
  private Long id;
  private String name;
  private String firstSurname;
  private String secondSurname;
  private String email;
  private String password;
  private Role role;
  private Boolean isVerified = false;

  public boolean isVerified() {
    return this.isVerified;
  }

  public User() {}

  public User(
      Long id,
      String name,
      String firstSurname,
      String secondSurname,
      String email,
      String password,
      Role role,
      Boolean isVerified) {
    this.id = id;
    this.name = name;
    this.firstSurname = firstSurname;
    this.secondSurname = secondSurname;
    this.email = email;
    this.password = password;
    this.role = role;
    this.isVerified = isVerified;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstSurname() {
    return firstSurname;
  }

  public void setFirstSurname(String firstSurname) {
    this.firstSurname = firstSurname;
  }

  public String getSecondSurname() {
    return secondSurname;
  }

  public void setSecondSurname(String secondSurname) {
    this.secondSurname = secondSurname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public boolean hasName() {
    return StringUtils.hasText(this.name);
  }

  public boolean hasFirstSurname() {
    return StringUtils.hasText(this.firstSurname);
  }

  public boolean hasPassword() {
    return StringUtils.hasText(this.password);
  }

  public Boolean getVerified() {
    return isVerified;
  }

  public void setVerified(Boolean verified) {
    isVerified = verified;
  }
}
