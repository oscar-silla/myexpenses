package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Expense;

public interface SaveExpenseUseCasePort {
  void execute(Expense expense);
}
