package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
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

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseFilterMock.EXPENSE_FILTER;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE_1;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSES_PAGE;
import static com.mypersonalbook.economy.utils.test.mocks.PaginationFilterMock.PAGINATION_FILTER;
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
    Executable executable = () -> this.expenseService.save(EXPENSE_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should save expense")
  void shouldSaveExpense() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    doNothing().when(this.expenseRepository).save(any(Expense.class));
    this.expenseService.save(EXPENSE_1);
    verify(this.expenseRepository).save(any(Expense.class));
  }

  @Test
  @DisplayName("Should delete expense by id")
  void shouldDeleteExpenseById() {
    doNothing().when(this.expenseRepository).deleteById(anyLong());
    this.expenseService.deleteById(EXPENSE_ID_1);
    verify(this.expenseRepository).deleteById(anyLong());
  }

  @Test
  @DisplayName("Should throw not found exception when find expense by id")
  void shouldThrowNotFoundException_WhenFindExpenseById() {
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());
    Executable executable = () -> this.expenseService.findById(EXPENSE_ID_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should return expense when find by id")
  void shouldReturnExpense_WhenFindById() {
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.of(EXPENSE_1));
    Expense expense = this.expenseService.findById(EXPENSE_ID_1);
    assertNotNull(expense);
  }

  @Test
  @DisplayName("Should return expenses page when find")
  void shouldReturnExpensesPage_WhenFind() {
    when(this.expenseRepository.find(any(ExpenseFilter.class), any(PaginationFilter.class)))
        .thenReturn(EXPENSES_PAGE);
    this.expenseService.find(EXPENSE_FILTER, PAGINATION_FILTER);
    verify(this.expenseRepository).find(any(ExpenseFilter.class), any(PaginationFilter.class));
  }

  @Test
  @DisplayName("Should throw not found exception when modify expense and category not exists")
  void shouldThrowNotFoundException_WhenModifyExpense_AndCategoryNotExists() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.empty());
    Executable executable = () -> this.expenseService.modify(EXPENSE_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should throw not found exception when modify expense and expense to update not exists")
  void shouldThrowNotFoundException_WhenModifyExpense_AndExpenseToUpdateNotExists() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.of(EXPENSE_CATEGORY));
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.empty());
    Executable executable = () -> this.expenseService.modify(EXPENSE_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should modify expense")
  void shouldModifyExpense() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.of(EXPENSE_CATEGORY));
    when(this.expenseRepository.findById(anyLong())).thenReturn(Optional.of(EXPENSE_1));
    doNothing().when(this.expenseRepository).modify(any(Expense.class), any(Expense.class));
    this.expenseService.modify(EXPENSE_1);
    verify(this.expenseRepository).modify(any(Expense.class), any(Expense.class));
  }
}
