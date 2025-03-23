package com.mypersonalbook.economy.domain;

import java.util.UUID;

public class Email {
  private String from;
  private String to;
  private String subject;
  private String text;
  private UUID code;

  public Email() {}

  public Email(String from, String to, String subject, String text, UUID code) {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.text = text;
    this.code = code;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public UUID getCode() {
    return code;
  }

  public void setCode(UUID code) {
    this.code = code;
  }
}
