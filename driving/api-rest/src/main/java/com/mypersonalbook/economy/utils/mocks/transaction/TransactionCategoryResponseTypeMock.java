package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionCategoryResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_COLOR;
import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_NAME_UPPER_CASE;

public class TransactionCategoryResponseTypeMock {
  public static TransactionCategoryResponseType TRANSACTION_CATEGORY_RESPONSE_TYPE =
      new TransactionCategoryResponseType(CATEGORY_NAME_UPPER_CASE, CATEGORY_COLOR);
}
