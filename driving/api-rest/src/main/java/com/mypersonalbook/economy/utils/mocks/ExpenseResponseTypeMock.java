package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.ExpenseResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class ExpenseResponseTypeMock {
  public static ExpenseResponseType EXPENSE_RESPONSE_TYPE() {
    ExpenseResponseType expenseResponseType = new ExpenseResponseType();
    expenseResponseType.setId(EXPENSE_ID);
    expenseResponseType.setAmount(EXPENSE_AMOUNT);
    expenseResponseType.setCategory(CATEGORY_EXPENSE_TYPE);
    expenseResponseType.setDescription(EXPENSE_DESCRIPTION);
    expenseResponseType.setDate(EXPENSE_DATE);
    return expenseResponseType;
  }
}
