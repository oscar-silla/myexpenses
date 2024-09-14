package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.models.ExpenseMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExpenseRepositoryMapperTest {
  ExpenseRepositoryMapper expenseRepositoryMapper;

  @BeforeEach
  void setUp() {
    CategoryRepositoryMapper categoryRepositoryMapper = new CategoryRepositoryMapperImpl();
    this.expenseRepositoryMapper = new ExpenseRepositoryMapperImpl(categoryRepositoryMapper);
  }

  @Test
  @DisplayName("Should map to expenseMO")
  void shouldMapToExpenseMO() {
    final ExpenseMO RESULT = this.expenseRepositoryMapper.toExpenseMO(EXPENSE);
    assertEquals(EXPENSE_ID, RESULT.getId());
    assertEquals(EXPENSE_AMOUNT, RESULT.getAmount());
    assertEquals(CATEGORY_ID, RESULT.getCategory().getId());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(CATEGORY_EXPENSE_TYPE, RESULT.getCategory().getType());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getDescription());
    assertEquals(EXPENSE_DATE, RESULT.getDate());
  }
}
