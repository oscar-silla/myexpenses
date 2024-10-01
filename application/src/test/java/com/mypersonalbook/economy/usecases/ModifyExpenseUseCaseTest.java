package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModifyExpenseUseCaseTest {
  ModifyExpenseUseCase modifyExpenseUseCase;
  @Mock private ExpenseService expenseService;

  @BeforeEach
  void setUp() {
    this.modifyExpenseUseCase = new ModifyExpenseUseCase(this.expenseService);
  }

  @Test
  @DisplayName("Should modify expense when execute use case")
  void shouldModifyExpense_WhenExecuteUseCase() {
    doNothing().when(this.expenseService).modify(any(Expense.class));
    this.modifyExpenseUseCase.execute(EXPENSE_1);
    verify(this.expenseService).modify(any(Expense.class));
  }
}
