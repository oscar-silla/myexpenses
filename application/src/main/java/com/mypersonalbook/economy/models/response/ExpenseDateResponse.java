package com.mypersonalbook.economy.models.response;

import com.mypersonalbook.economy.domain.Expense;

import java.time.LocalDate;
import java.util.List;

public class ExpenseDateResponse {
  private LocalDate date;
  private List<Expense> expenses;

  public ExpenseDateResponse() {}

  public ExpenseDateResponse(LocalDate date, List<Expense> expenses) {
    this.date = date;
    this.expenses = expenses;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }
}
