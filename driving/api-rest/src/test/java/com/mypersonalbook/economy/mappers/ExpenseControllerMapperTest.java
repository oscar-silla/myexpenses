package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.ExpenseRequestBodyTypeMock.EXPENSE_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
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
    assertEquals(EXPENSE_CATEGORY, RESULT.category());
    assertEquals(EXPENSE_DATE, RESULT.date());
  }
}
