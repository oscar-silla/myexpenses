package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
  private final ExpenseRepositoryPort expenseRepository;
  private final CategoryRepositoryPort categoryRepository;

  public ExpenseService(
      ExpenseRepositoryPort expenseRepository, CategoryRepositoryPort categoryRepository) {
    this.expenseRepository = expenseRepository;
    this.categoryRepository = categoryRepository;
  }

  public void save(Expense expense) {
    this.categoryRepository
        .findOne(new CategoryFilter(expense.category().name(), expense.category().type()))
        .orElseThrow(NotFoundException::new);
    this.expenseRepository.save(expense);
  }
}
