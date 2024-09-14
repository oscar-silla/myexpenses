package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.mappers.ExpenseControllerMapper;
import com.mypersonalbook.economy.ports.in.SaveExpenseUseCasePort;
import openapi.economy.model.ExpenseRequestBodyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mypersonalbook.economy.utils.mocks.ExpenseRequestBodyTypeMock.EXPENSE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerAdapterTest {
  ExpenseControllerAdapter expenseControllerAdapter;

  @Mock private ExpenseControllerMapper expenseControllerMapper;
  @Mock private SaveExpenseUseCasePort saveExpenseUseCase;

  @BeforeEach
  void setUp() {
    this.expenseControllerAdapter =
        new ExpenseControllerAdapter(this.expenseControllerMapper, this.saveExpenseUseCase);
  }

  @Test
  @DisplayName("Should return 200 status code when post expense")
  void shouldReturn200StatusCode_WhenPostExpense() {
    when(this.expenseControllerMapper.toExpense(any(ExpenseRequestBodyType.class)))
        .thenReturn(EXPENSE);
    doNothing().when(this.saveExpenseUseCase).execute(any(Expense.class));
    final ResponseEntity<Void> RESULT =
        this.expenseControllerAdapter.postExpense(EXPENSE_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.CREATED, RESULT.getStatusCode());
  }
}
