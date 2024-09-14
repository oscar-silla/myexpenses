package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.ExpenseMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMOMock.CATEGORY_MO;

public class ExpenseMOMock {
  public static final ExpenseMO EXPENSE_MO =
      new ExpenseMO(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_DESCRIPTION, EXPENSE_DATE, CATEGORY_MO);
}
