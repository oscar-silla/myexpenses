package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.mappers.ExpenseRepositoryMapper;
import com.mypersonalbook.economy.models.ExpenseMO;
import com.mypersonalbook.economy.repositories.ExpenseJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMOMock.EXPENSE_MO;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseRepositoryAdapterTest {
  ExpenseRepositoryAdapter expenseRepositoryAdapter;
  @Mock private ExpenseRepositoryMapper expenseRepositoryMapper;
  @Mock private ExpenseJpaRepository expenseJpaRepository;

  @BeforeEach
  void setUp() {
    this.expenseRepositoryAdapter =
        new ExpenseRepositoryAdapter(this.expenseJpaRepository, this.expenseRepositoryMapper);
  }

  @Test
  @DisplayName("Should save with JPA when save")
  void shouldSave_WithJPA_WhenSave() {
    when(this.expenseRepositoryMapper.toExpenseMO(any(Expense.class))).thenReturn(EXPENSE_MO);
    when(this.expenseJpaRepository.save(any(ExpenseMO.class))).thenReturn(EXPENSE_MO);
    this.expenseRepositoryAdapter.save(EXPENSE);
    verify(this.expenseJpaRepository).save(any(ExpenseMO.class));
  }
}
