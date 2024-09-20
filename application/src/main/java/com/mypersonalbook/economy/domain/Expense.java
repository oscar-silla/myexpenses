package com.mypersonalbook.economy.domain;

import java.time.LocalDate;

public record Expense(
    Long id, Float amount, Category category, String description, LocalDate date) {
  public Expense setCategory(Category category) {
    return new Expense(this.id, this.amount, category, this.description, this.date);
  }
}
