package com.mypersonalbook.economy.domain;

import static com.mypersonalbook.economy.utils.AppConstants.EXPENSE_TYPE;
import static com.mypersonalbook.economy.utils.AppConstants.REVENUE_TYPE;

import java.time.LocalDate;
import org.springframework.util.StringUtils;

public class Transaction {
  Long id;
  Float amount;
  Category category;
  String description;
  LocalDate date;
  String type;
  User user;

  public Transaction() {}

  public Transaction(
      Long id,
      Float amount,
      Category category,
      String description,
      LocalDate date,
      String type,
      User user) {
    this.id = id;
    this.amount = amount;
    this.category = category;
    this.description = description;
    this.date = date;
    this.type = type;
    this.user = user;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public boolean hasAmount() {
    return this.amount != null;
  }

  public boolean hasCategory() {
    return this.category != null && StringUtils.hasText(this.getCategory().getName());
  }

  public boolean hasEmptyDescription() {
    return this.getDescription() != null && this.getDescription().isBlank();
  }

  public boolean hasDate() {
    return this.date != null;
  }

  public boolean hasType() {
    return this.getType() != null
        && (this.getType().equalsIgnoreCase(EXPENSE_TYPE)
            || this.getType().equalsIgnoreCase(REVENUE_TYPE));
  }
}
