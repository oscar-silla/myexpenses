package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionResponseType;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionCategoryResponseTypeMock.TRANSACTION_CATEGORY_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionResponseTypeMock {
  public static TransactionResponseType TRANSACTION_RESPONSE_TYPE() {
    TransactionResponseType expenseResponseType = new TransactionResponseType();
    expenseResponseType.setId(TRANSACTION_ID_1);
    expenseResponseType.setAmount(EXPENSE_TRANSACTION_AMOUNT);
    expenseResponseType.setCategory(TRANSACTION_CATEGORY_RESPONSE_TYPE);
    expenseResponseType.setDescription(TRANSACTION_DESCRIPTION);
    expenseResponseType.setDate(TRANSACTION_DATE_1);
    return expenseResponseType;
  }
}
