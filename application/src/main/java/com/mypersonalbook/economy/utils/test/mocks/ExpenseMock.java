package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Expense;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class ExpenseMock {
  public static final Expense EXPENSE =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
}
