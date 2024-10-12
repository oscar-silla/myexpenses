package com.mypersonalbook.economy.models.response;

import com.mypersonalbook.economy.domain.Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionDateResponse {
  private LocalDate date;
  private List<Transaction> transactions;

  public TransactionDateResponse() {}

  public TransactionDateResponse(LocalDate date, List<Transaction> transactions) {
    this.date = date;
    this.transactions = transactions;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Transaction> getExpenses() {
    return transactions;
  }

  public void setExpenses(List<Transaction> transactions) {
    this.transactions = transactions;
  }
}
