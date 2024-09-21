package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.models.ExpenseMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMOMock.EXPENSE_MO;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.OTHER_EXPENSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

  @Test
  @DisplayName("Should map to expense")
  void shouldMapToExpense() {
    final Expense RESULT = this.expenseRepositoryMapper.toExpense(EXPENSE_MO);
    assertEquals(EXPENSE_ID, RESULT.getId());
    assertEquals(EXPENSE_AMOUNT, RESULT.getAmount());
    assertEquals(CATEGORY_ID, RESULT.getCategory().getId());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(CATEGORY_EXPENSE_TYPE, RESULT.getCategory().getType());
    assertEquals(EXPENSE_DESCRIPTION, RESULT.getDescription());
    assertEquals(EXPENSE_DATE, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map from dto to expense with expense null properties")
  void shouldMapFromDtoToExpense_WithExpenseNullProperties() {
    Expense expenseToUpdate = new Expense(null, null, null, null, null);
    this.expenseRepositoryMapper.mapFromDtoToExpense(EXPENSE, expenseToUpdate);
    assertEquals(EXPENSE_ID, expenseToUpdate.getId());
    assertEquals(EXPENSE_AMOUNT, expenseToUpdate.getAmount());
    assertEquals(CATEGORY_ID, expenseToUpdate.getCategory().getId());
    assertEquals(CATEGORY_NAME, expenseToUpdate.getCategory().getName());
    assertEquals(CATEGORY_EXPENSE_TYPE, expenseToUpdate.getCategory().getType());
    assertEquals(EXPENSE_DESCRIPTION, expenseToUpdate.getDescription());
    assertEquals(EXPENSE_DATE, expenseToUpdate.getDate());
  }

  @Test
  @DisplayName("Should map from dto to expense with expense")
  void shouldMapFromDtoToExpense_WithExpense() {
    Category category = new Category(1L, "CATEGORY", "EXPENSE");
    Expense expenseToUpdate =
            new Expense(
                    1L, 2.5F, category, "EXPENSE_DESCRIPTION", LocalDate.now());
    this.expenseRepositoryMapper.mapFromDtoToExpense(OTHER_EXPENSE, expenseToUpdate);
    assertEquals(EXPENSE_ID_2, expenseToUpdate.getId());
    assertEquals(EXPENSE_AMOUNT_2, expenseToUpdate.getAmount());
    assertEquals(CATEGORY_ID_2, expenseToUpdate.getCategory().getId());
    assertEquals(CATEGORY_NAME_2, expenseToUpdate.getCategory().getName());
    assertEquals(CATEGORY_EXPENSE_TYPE_2, expenseToUpdate.getCategory().getType());
    assertEquals(EXPENSE_DESCRIPTION_2, expenseToUpdate.getDescription());
    assertEquals(EXPENSE_DATE_2, expenseToUpdate.getDate());
  }

  @Test
  @DisplayName("Should map from dto to expense with dto null properties")
  void shouldMapFromDtoToExpense_WithNullDtoProperties() {
    Expense dto = new Expense(null, null, null, null, null);
    Expense expenseToUpdate =
            new Expense(
                    EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
    this.expenseRepositoryMapper.mapFromDtoToExpense(dto, expenseToUpdate);
    assertNotNull(expenseToUpdate.getId());
    assertNotNull(expenseToUpdate.getAmount());
    assertNotNull(expenseToUpdate.getCategory());
    assertNotNull(expenseToUpdate.getCategory().getId());
    assertNotNull(expenseToUpdate.getCategory().getName());
    assertNotNull(expenseToUpdate.getCategory().getType());
    assertNotNull(expenseToUpdate.getDescription());
    assertNotNull(expenseToUpdate.getDate());
  }
}
