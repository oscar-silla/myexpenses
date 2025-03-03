package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

@Entity(name = "UserMO")
@Table(name = "o_users")
public class UserMO {
  @Id
  @SequenceGenerator(name = "o_users_seq", sequenceName = "O_USERS_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "o_users_seq")
  @Column(name = "user_id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "first_surname", nullable = false)
  private String firstSurname;

  @Column(name = "second_surname")
  private String secondSurname;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  public UserMO() {}

  public UserMO(
      Long id,
      String name,
      String firstSurname,
      String secondSurname,
      String email,
      String password) {
    this.id = id;
    this.name = name;
    this.firstSurname = firstSurname;
    this.secondSurname = secondSurname;
    this.email = email;
    this.password = password;
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
}
