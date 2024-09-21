package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.filters.ExpenseFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.END_DATE;
import static com.mypersonalbook.economy.utils.test.TestConstants.START_DATE;

public class ExpenseFilterMock {
  public static final ExpenseFilter EXPENSE_FILTER = new ExpenseFilter(START_DATE, END_DATE);
}
