package com.mypersonalbook.economy.ports.out;

import com.mypersonalbook.economy.domain.Expense;

import java.util.Optional;

public interface ExpenseRepositoryPort {
  void save(Expense expense);

  void deleteById(Long id);

  Optional<Expense> findById(Long id);
}
