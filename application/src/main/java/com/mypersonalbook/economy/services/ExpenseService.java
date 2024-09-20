package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.mypersonalbook.economy.utils.AppConstants.EXPENSE_TYPE;

@Service
public class ExpenseService {
  private final ExpenseRepositoryPort expenseRepository;
  private final CategoryRepositoryPort categoryRepository;
  private final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

  public ExpenseService(
      ExpenseRepositoryPort expenseRepository, CategoryRepositoryPort categoryRepository) {
    this.expenseRepository = expenseRepository;
    this.categoryRepository = categoryRepository;
  }

  public void save(Expense expense) {
    Category category =
        this.categoryRepository
            .findOne(new CategoryFilter(expense.category().name(), EXPENSE_TYPE))
            .orElseThrow(
                () -> {
                  logger.error(
                      "Category with name: {}, type: {} not found.",
                      expense.category().name(),
                      EXPENSE_TYPE);
                  return new NotFoundException();
                });
    Expense expenseToSave = expense.setCategory(category);
    this.expenseRepository.save(expenseToSave);
  }

  public void deleteById(Long id) {
    this.expenseRepository.deleteById(id);
  }

  public Expense findById(Long id) {
    return this.expenseRepository
        .findById(id)
        .orElseThrow(
            () -> {
              logger.error("Expense with id: {} not found.", id);
              return new NotFoundException();
            });
  }
}
