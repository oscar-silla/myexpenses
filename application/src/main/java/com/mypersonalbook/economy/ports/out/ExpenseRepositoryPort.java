package com.mypersonalbook.economy.ports.out;

import com.mypersonalbook.economy.domain.Expense;

public interface ExpenseRepositoryPort {
  void save(Expense expense);
  void deleteById(Long id);
}
