package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Expense;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EMPTY_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;

public class ExpenseMock {
  public static final Expense EXPENSE =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_AMOUNT =
      new Expense(EXPENSE_ID, null, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_CATEGORY =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, null, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_EMPTY_CATEGORY =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EMPTY_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_EMPTY_DESCRIPTION =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, "  ", EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_DATE =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, null);
}
