package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Expense;

public interface ModifyExpenseUseCasePort {
  void execute(Expense expense);
}
