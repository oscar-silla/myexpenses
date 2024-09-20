package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import openapi.economy.model.ExpenseResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.ExpenseRequestBodyTypeMock.EXPENSE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
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
    assertEquals(EXPENSE_AMOUNT, RESULT.amount());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.description());
    assertEquals(CATEGORY_NAME, RESULT.category().name());
    assertEquals(EXPENSE_DATE, RESULT.date());
  }

  @Test
  @DisplayName("Should map to expense response type")
  void shouldMapToExpenseResponseType() {
    final ExpenseResponseType RESULT = this.expenseControllerMapper.toExpenseResponseType(EXPENSE);
    assertEquals(EXPENSE_AMOUNT, RESULT.getAmount());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME, RESULT.getCategory());
    assertEquals(EXPENSE_DATE, RESULT.getDate());
  }
}
