package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.DeleteExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_ID_1;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteExpenseUseCaseTest {
  DeleteExpenseUseCasePort deleteExpenseUseCase;
  @Mock private ExpenseService expenseService;

  @BeforeEach
  void setUp() {
    this.deleteExpenseUseCase = new DeleteExpenseUseCase(this.expenseService);
  }

  @Test
  @DisplayName("Should delete expense by id")
  void shouldDeleteExpenseById() {
    doNothing().when(this.expenseService).deleteById(anyLong());
    this.deleteExpenseUseCase.execute(EXPENSE_ID_1);
    verify(this.expenseService).deleteById(anyLong());
  }
}
