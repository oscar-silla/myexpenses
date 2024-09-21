package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.mappers.ExpenseControllerMapper;
import com.mypersonalbook.economy.ports.in.*;
import openapi.economy.model.ExpenseRequestBodyType;
import openapi.economy.model.ExpenseResponseType;
import openapi.economy.model.ExpensesResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.mocks.ExpenseRequestBodyTypeMock.EXPENSE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.ExpenseResponseTypeMock.EXPENSE_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.ExpensesResponseTypeMock.EXPENSES_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSES_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerAdapterTest {
  ExpenseControllerAdapter expenseControllerAdapter;

  @Mock private ExpenseControllerMapper expenseControllerMapper;
  @Mock private SaveExpenseUseCasePort saveExpenseUseCase;
  @Mock private DeleteExpenseUseCasePort deleteExpenseUseCase;
  @Mock private GetExpenseUseCasePort getExpenseUseCase;
  @Mock private GetExpensesUseCasePort getExpensesUseCase;
  @Mock private ModifyExpenseUseCasePort modifyExpenseUseCase;

  @BeforeEach
  void setUp() {
    this.expenseControllerAdapter =
        new ExpenseControllerAdapter(
            this.expenseControllerMapper,
            this.saveExpenseUseCase,
            this.deleteExpenseUseCase,
            this.getExpenseUseCase,
            this.getExpensesUseCase,
            this.modifyExpenseUseCase);
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

  @Test
  @DisplayName("Should return 204 status code when delete expense")
  void shouldReturn204StatusCode_WhenDeleteExpense() {
    doNothing().when(this.deleteExpenseUseCase).execute(anyLong());
    final ResponseEntity<Void> RESULT = this.expenseControllerAdapter.deleteExpense(EXPENSE_ID);
    assertEquals(HttpStatus.NO_CONTENT, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when get expense")
  void shouldReturn200StatusCode_WhenGetExpense() {
    when(this.getExpenseUseCase.execute(anyLong())).thenReturn(EXPENSE);
    when(this.expenseControllerMapper.toExpenseResponseType(any(Expense.class)))
        .thenReturn(EXPENSE_RESPONSE_TYPE());
    final ResponseEntity<ExpenseResponseType> RESULT =
        this.expenseControllerAdapter.getExpense(EXPENSE_ID);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when get expenses")
  void shouldReturn200StatusCode_WhenGetExpenses() {
    when(this.getExpensesUseCase.execute(
            anyInt(), anyInt(), any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(EXPENSES_PAGE);
    when(this.expenseControllerMapper.toExpensesResponseType(any(PageImpl.class)))
        .thenReturn(EXPENSES_RESPONSE_TYPE());
    final ResponseEntity<ExpensesResponseType> RESULT =
        this.expenseControllerAdapter.getExpenses(PAGE_NUMBER, PAGE_SIZE, START_DATE, END_DATE);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when patch expense")
  void shouldReturn200StatusCode_WhenPatchExpense() {
    when(this.expenseControllerMapper.toExpense(any(ExpenseRequestBodyType.class)))
        .thenReturn(EXPENSE);
    doNothing().when(this.modifyExpenseUseCase).execute(any(Expense.class));
    final ResponseEntity<Void> RESULT =
        this.expenseControllerAdapter.patchExpense(EXPENSE_ID, EXPENSE_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }
}
