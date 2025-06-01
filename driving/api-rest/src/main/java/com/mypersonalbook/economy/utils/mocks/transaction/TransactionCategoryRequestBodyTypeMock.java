package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionCategoryRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_COLOR;
import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_NAME_UPPER_CASE;

public class TransactionCategoryRequestBodyTypeMock {
  public static TransactionCategoryRequestBodyType TRANSACTION_CATEGORY_REQUEST_BODY_TYPE() {
    TransactionCategoryRequestBodyType transactionCategoryRequestBodyType =
        new TransactionCategoryRequestBodyType();
    transactionCategoryRequestBodyType.setName(CATEGORY_NAME_UPPER_CASE);
    transactionCategoryRequestBodyType.setColor(CATEGORY_COLOR);
    return transactionCategoryRequestBodyType;
  }
}
