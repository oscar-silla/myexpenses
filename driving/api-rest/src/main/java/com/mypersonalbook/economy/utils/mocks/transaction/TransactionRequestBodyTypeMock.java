package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionRequestBodyType;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionCategoryRequestBodyTypeMock.TRANSACTION_CATEGORY_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionRequestBodyTypeMock {
  public static TransactionRequestBodyType EXPENSE_TRANSACTION_REQUEST_BODY_TYPE() {
    TransactionRequestBodyType expenseRequestBodyType = new TransactionRequestBodyType();
    expenseRequestBodyType.setDate(TRANSACTION_DATE_1);
    expenseRequestBodyType.setAmount(EXPENSE_TRANSACTION_AMOUNT);
    expenseRequestBodyType.setCategory(TRANSACTION_CATEGORY_REQUEST_BODY_TYPE());
    expenseRequestBodyType.setDescription(TRANSACTION_DESCRIPTION);
    expenseRequestBodyType.setType(CATEGORY_EXPENSE_TRANSACTION_TYPE_LOWER_CASE);
    return expenseRequestBodyType;
  }

  public static TransactionRequestBodyType REVENUE_TRANSACTION_REQUEST_BODY_TYPE() {
    TransactionRequestBodyType revenueRequestBodyType = new TransactionRequestBodyType();
    revenueRequestBodyType.setDate(TRANSACTION_DATE_1);
    revenueRequestBodyType.setAmount(REVENUE_TRANSACTION_AMOUNT);
    revenueRequestBodyType.setCategory(TRANSACTION_CATEGORY_REQUEST_BODY_TYPE());
    revenueRequestBodyType.setDescription(TRANSACTION_DESCRIPTION);
    revenueRequestBodyType.setType(CATEGORY_REVENUE_TRANSACTION_TYPE_UPPER_CASE);
    return revenueRequestBodyType;
  }
}
