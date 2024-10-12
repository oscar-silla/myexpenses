package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.TransactionResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionResponseTypeMock {
  public static TransactionResponseType TRANSACTION_RESPONSE_TYPE() {
    TransactionResponseType expenseResponseType = new TransactionResponseType();
    expenseResponseType.setId(TRANSACTION_ID_1);
    expenseResponseType.setAmount(TRANSACTION_AMOUNT);
    expenseResponseType.setCategory(CATEGORY_TRANSACTION_TYPE);
    expenseResponseType.setDescription(TRANSACTION_DESCRIPTION);
    expenseResponseType.setDate(TRANSACTION_DATE_1);
    return expenseResponseType;
  }
}
