package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import openapi.economy.model.ExpenseResponseType;
import openapi.economy.model.ExpensesResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.ExpenseRequestBodyTypeMock.EXPENSE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE_1;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.ExpenseDateResponseMock.EXPENSE_DATE_RESPONSE_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerMapperTest {
  ExpenseControllerMapper expenseControllerMapper;

  @BeforeEach
  void setUp() {
    this.expenseControllerMapper = new ExpenseControllerMapperImpl();
  }

  @Test
  @DisplayName("Should map to expense")
  void shouldMapToExpense() {
    final Expense RESULT = this.expenseControllerMapper.toExpense(EXPENSE_REQUEST_BODY_TYPE());
    assertEquals(EXPENSE_AMOUNT, RESULT.getAmount());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(EXPENSE_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map to expense response type")
  void shouldMapToExpenseResponseType() {
    final ExpenseResponseType RESULT = this.expenseControllerMapper.toExpenseResponseType(EXPENSE_1);
    assertEquals(EXPENSE_AMOUNT, RESULT.getAmount());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME, RESULT.getCategory());
    assertEquals(EXPENSE_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map to expenses response type")
  void shouldMapToExpensesResponseType() {
    final ExpensesResponseType RESULT =
        this.expenseControllerMapper.toExpenseDateResponseType(EXPENSE_DATE_RESPONSE_PAGE);
    assertEquals(0, RESULT.getPagination().getPageNumber());
    assertEquals(1, RESULT.getPagination().getPageSize());
    assertEquals(1, RESULT.getPagination().getTotalResults());
    assertEquals(EXPENSE_DATE_1, RESULT.getResults().get(0).getDate());
    assertEquals(EXPENSE_ID_1, RESULT.getResults().get(0).getExpenses().get(0).getId());
    assertEquals(EXPENSE_AMOUNT, RESULT.getResults().get(0).getExpenses().get(0).getAmount());
    assertEquals(CATEGORY_NAME, RESULT.getResults().get(0).getExpenses().get(0).getCategory());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getResults().get(0).getExpenses().get(0).getDescription());
  }
}
