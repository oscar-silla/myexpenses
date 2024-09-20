package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.mappers.ExpenseRepositoryMapper;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import com.mypersonalbook.economy.repositories.ExpenseJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseRepositoryAdapter implements ExpenseRepositoryPort {
  private final ExpenseJpaRepository expenseJpaRepository;
  private final ExpenseRepositoryMapper expenseRepositoryMapper;

  public ExpenseRepositoryAdapter(
      ExpenseJpaRepository expenseJpaRepository, ExpenseRepositoryMapper expenseRepositoryMapper) {
    this.expenseJpaRepository = expenseJpaRepository;
    this.expenseRepositoryMapper = expenseRepositoryMapper;
  }

  @Override
  public void save(Expense expense) {
    this.expenseJpaRepository.save(this.expenseRepositoryMapper.toExpenseMO(expense));
  }

  @Override
  public void deleteById(Long id) {
    this.expenseJpaRepository.deleteById(id);
  }

  @Override
  public Optional<Expense> findById(Long id) {
    return this.expenseJpaRepository.findById(id).map(this.expenseRepositoryMapper::toExpense);
  }
}
