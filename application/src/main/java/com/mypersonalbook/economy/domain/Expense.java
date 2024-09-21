package com.mypersonalbook.economy.domain;

import java.time.LocalDate;

public class Expense {
  Long id;
  Float amount;
  Category category;
  String description;
  LocalDate date;

  public Expense() {}

  public Expense(
      Long id, Float amount, Category category, String description, LocalDate date) {
    this.id = id;
    this.amount = amount;
    this.category = category;
    this.description = description;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Expense{"
        + "id="
        + id
        + ", amount="
        + amount
        + ", category="
        + category
        + ", description='"
        + description
        + '\''
        + ", date="
        + date
        + '}';
  }
}
