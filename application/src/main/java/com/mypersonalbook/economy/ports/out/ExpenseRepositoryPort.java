package com.mypersonalbook.economy.ports.out;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ExpenseRepositoryPort {
  void save(Expense expense);

  void deleteById(Long id);

  Optional<Expense> findById(Long id);

  Page<Expense> find(ExpenseFilter expenseFilter, PaginationFilter paginationFilter);
}
