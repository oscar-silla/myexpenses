package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_ID;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
  ExpenseService expenseService;
  @Mock private ExpenseRepositoryPort expenseRepository;
  @Mock private CategoryRepositoryPort categoryRepository;

  @BeforeEach
  void setUp() {
    this.expenseService = new ExpenseService(this.expenseRepository, this.categoryRepository);
  }

  @Test
  @DisplayName("Should throw not found exception when save")
  void shouldThrowNotFoundException_WhenSave() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.empty());
    Executable executable = () -> this.expenseService.save(EXPENSE);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should save expense")
  void shouldSaveExpense() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    doNothing().when(this.expenseRepository).save(any(Expense.class));
    this.expenseService.save(EXPENSE);
    verify(this.expenseRepository).save(any(Expense.class));
  }

  @Test
  @DisplayName("Should delete expense by id")
  void shouldDeleteExpenseById() {
    doNothing().when(this.expenseRepository).deleteById(anyLong());
    this.expenseService.deleteById(EXPENSE_ID);
    verify(this.expenseRepository).deleteById(anyLong());
  }

  @Test
  @DisplayName("Should throw not found exception when find expense by id")
  void shouldThrowNotFoundException_WhenFindExpenseById() {
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());
    Executable executable = () -> this.expenseService.findById(EXPENSE_ID);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should return expense when find by id")
  void shouldReturnExpense_WhenFindById() {
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.of(EXPENSE));
    Expense expense = this.expenseService.findById(EXPENSE_ID);
    assertNotNull(expense);
  }
}
