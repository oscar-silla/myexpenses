package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.mappers.ExpenseRepositoryMapper;
import com.mypersonalbook.economy.models.ExpenseMO;
import com.mypersonalbook.economy.repositories.ExpenseJpaRepository;
import com.mypersonalbook.economy.specifications.ExpenseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseFilterMock.EXPENSE_FILTER;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMOMock.EXPENSES_PAGE_MO;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMOMock.EXPENSE_MO;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.*;
import static com.mypersonalbook.economy.utils.test.mocks.PaginationFilterMock.PAGINATION_FILTER;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseRepositoryAdapterTest {
  ExpenseRepositoryAdapter expenseRepositoryAdapter;
  @Mock private ExpenseJpaRepository expenseJpaRepository;
  @Mock private ExpenseSpecification expenseSpecification;
  @Mock private ExpenseRepositoryMapper expenseRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.expenseRepositoryAdapter =
        new ExpenseRepositoryAdapter(
            this.expenseJpaRepository, this.expenseSpecification, this.expenseRepositoryMapper);
  }

  @Test
  @DisplayName("Should save with JPA when save")
  void shouldSave_WithJPA_WhenSave() {
    when(this.expenseRepositoryMapper.toExpenseMO(any(Expense.class))).thenReturn(EXPENSE_MO);
    when(this.expenseJpaRepository.save(any(ExpenseMO.class))).thenReturn(EXPENSE_MO);
    this.expenseRepositoryAdapter.save(EXPENSE_1);
    verify(this.expenseJpaRepository).save(any(ExpenseMO.class));
  }

  @Test
  @DisplayName("Should delete expense by id")
  void shouldDeleteExpenseById() {
    doNothing().when(this.expenseJpaRepository).deleteById(anyLong());
    this.expenseRepositoryAdapter.deleteById(EXPENSE_ID_1);
    verify(this.expenseJpaRepository).deleteById(anyLong());
  }

  @Test
  @DisplayName("Should return expense when find by id")
  void shouldReturnExpense_WhenFindById() {
    when(this.expenseJpaRepository.findById(anyLong())).thenReturn(Optional.of(EXPENSE_MO));
    when(this.expenseRepositoryMapper.toExpense(any(ExpenseMO.class))).thenReturn(EXPENSE_1);
    final Optional<Expense> RESULT = this.expenseRepositoryAdapter.findById(EXPENSE_ID_1);
    assertTrue(RESULT.isPresent());
  }

  @Test
  @DisplayName("Should return expenses page when find")
  void shouldReturnExpensesPage_WhenFind() {
    Specification<ExpenseMO> specifications =
        (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    when(this.expenseSpecification.getSpecification(any(ExpenseFilter.class)))
        .thenReturn(specifications);
    when(this.expenseJpaRepository.findAll(any(Specification.class), any(PageRequest.class)))
        .thenReturn(EXPENSES_PAGE_MO);
    when(this.expenseRepositoryMapper.toExpense(any(ExpenseMO.class))).thenReturn(EXPENSE_1);
    this.expenseRepositoryAdapter.find(EXPENSE_FILTER, PAGINATION_FILTER);
    verify(this.expenseRepositoryMapper).toExpense(any(ExpenseMO.class));
  }

  @Test
  @DisplayName("Should save expense when modify")
  void shouldSaveExpense_WhenModify() {
    doNothing()
        .when(this.expenseRepositoryMapper)
        .mapFromDtoToExpense(any(Expense.class), any(Expense.class));
    when(this.expenseRepositoryMapper.toExpenseMO(any(Expense.class))).thenReturn(EXPENSE_MO);
    when(this.expenseJpaRepository.save(any(ExpenseMO.class))).thenReturn(EXPENSE_MO);
    this.expenseRepositoryAdapter.modify(EXPENSE_1, OTHER_EXPENSE);
    verify(this.expenseJpaRepository).save(any(ExpenseMO.class));
  }
}
