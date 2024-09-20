package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Expense;

public interface GetExpenseUseCasePort {
  Expense execute(Long id);
}
