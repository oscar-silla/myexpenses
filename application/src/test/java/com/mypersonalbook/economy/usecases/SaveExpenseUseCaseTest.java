package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.ports.in.SaveExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveExpenseUseCaseTest {
  SaveExpenseUseCasePort saveExpenseUseCase;
  @Mock private ExpenseService expenseService;

  @BeforeEach
  void setUp() {
    this.saveExpenseUseCase = new SaveExpenseUseCase(this.expenseService);
  }

  @Test
  @DisplayName("Should throw bad request exception when amount is null")
  void shouldThrowBadRequestException_WhenAmountIsNull() {
    final Executable EXECUTABLE = () -> this.saveExpenseUseCase.execute(EXPENSE_WITH_NULL_AMOUNT);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when category is null")
  void shouldThrowBadRequestException_WhenCategoryIsNull() {
    final Executable EXECUTABLE = () -> this.saveExpenseUseCase.execute(EXPENSE_WITH_NULL_CATEGORY);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when category is empty")
  void shouldThrowBadRequestException_WhenCategoryIsEmpty() {
    final Executable EXECUTABLE =
        () -> this.saveExpenseUseCase.execute(EXPENSE_WITH_EMPTY_CATEGORY);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when description is empty")
  void shouldThrowBadRequestException_WhenDescriptionIsEmpty() {
    final Executable EXECUTABLE =
        () -> this.saveExpenseUseCase.execute(EXPENSE_WITH_EMPTY_DESCRIPTION);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when date is null")
  void shouldThrowBadRequestException_WhenDateIsNull() {
    final Executable EXECUTABLE = () -> this.saveExpenseUseCase.execute(EXPENSE_WITH_NULL_DATE);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should save expense when execute")
  void shouldSaveExpense_WhenExecute() {
    doNothing().when(this.expenseService).save(any(Expense.class));
    this.saveExpenseUseCase.execute(EXPENSE_1);
    verify(this.expenseService).save(any(Expense.class));
  }
}
