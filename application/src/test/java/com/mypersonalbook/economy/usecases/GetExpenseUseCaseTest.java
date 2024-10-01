package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.GetExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE_1;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetExpenseUseCaseTest {
  GetExpenseUseCasePort getExpenseUseCase;
  @Mock private ExpenseService expenseService;

  @BeforeEach
  void setUp() {
    this.getExpenseUseCase = new GetExpenseUseCase(this.expenseService);
  }

  @Test
  @DisplayName("Should return expense when find by id")
  void shouldReturnExpenseWhenFindById() {
    when(this.expenseService.findById(anyLong())).thenReturn(EXPENSE_1);
    this.getExpenseUseCase.execute(EXPENSE_ID_1);
    verify(this.expenseService).findById(anyLong());
  }
}
