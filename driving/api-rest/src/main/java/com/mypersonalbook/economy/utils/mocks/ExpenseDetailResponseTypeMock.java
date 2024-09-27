package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.ExpenseDetailResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class ExpenseDetailResponseTypeMock {
  public static ExpenseDetailResponseType EXPENSE_DETAIL_RESPONSE_TYPE() {
    ExpenseDetailResponseType expenseDetailResponseType = new ExpenseDetailResponseType();
    expenseDetailResponseType.setId(EXPENSE_ID);
    expenseDetailResponseType.setAmount(EXPENSE_AMOUNT);
    expenseDetailResponseType.setCategory(CATEGORY_EXPENSE_TYPE);
    expenseDetailResponseType.setDescription(EXPENSE_DESCRIPTION);
    return expenseDetailResponseType;
  }
}
