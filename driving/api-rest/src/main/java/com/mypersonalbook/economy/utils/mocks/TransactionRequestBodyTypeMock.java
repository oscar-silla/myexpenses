package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.TransactionRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionRequestBodyTypeMock {
  public static TransactionRequestBodyType TRANSACTION_REQUEST_BODY_TYPE() {
    TransactionRequestBodyType expenseRequestBodyType = new TransactionRequestBodyType();
    expenseRequestBodyType.setDate(TRANSACTION_DATE_1);
    expenseRequestBodyType.setAmount(TRANSACTION_AMOUNT);
    expenseRequestBodyType.setCategory(CATEGORY_NAME);
    expenseRequestBodyType.setDescription(TRANSACTION_DESCRIPTION);
    expenseRequestBodyType.setType(CATEGORY_TRANSACTION_TYPE);
    return expenseRequestBodyType;
  }
}
