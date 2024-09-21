package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.mappers.ExpenseRepositoryMapper;
import com.mypersonalbook.economy.models.ExpenseMO;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import com.mypersonalbook.economy.repositories.ExpenseJpaRepository;
import com.mypersonalbook.economy.specifications.ExpenseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseRepositoryAdapter implements ExpenseRepositoryPort {
  private final ExpenseJpaRepository expenseJpaRepository;
  private final ExpenseSpecification expenseSpecification;
  private final ExpenseRepositoryMapper expenseRepositoryMapper;

  public ExpenseRepositoryAdapter(
      ExpenseJpaRepository expenseJpaRepository,
      ExpenseSpecification expenseSpecification,
      ExpenseRepositoryMapper expenseRepositoryMapper) {
    this.expenseJpaRepository = expenseJpaRepository;
    this.expenseSpecification = expenseSpecification;
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

  @Override
  public Page<Expense> find(ExpenseFilter expenseFilter, PaginationFilter paginationFilter) {
    Specification<ExpenseMO> specifications =
        this.expenseSpecification.getSpecification(expenseFilter);
    Pageable pageable =
        PageRequest.of(paginationFilter.pageNumber() - 1, paginationFilter.pageSize());
    Page<ExpenseMO> expensesMOPage = this.expenseJpaRepository.findAll(specifications, pageable);
    return expensesMOPage.map(this.expenseRepositoryMapper::toExpense);
  }
}
