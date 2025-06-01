package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionDetailResponseType;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionCategoryResponseTypeMock.TRANSACTION_CATEGORY_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionDetailResponseTypeMock {
  public static TransactionDetailResponseType TRANSACTION_DETAIL_RESPONSE_TYPE() {
    TransactionDetailResponseType expenseDetailResponseType = new TransactionDetailResponseType();
    expenseDetailResponseType.setId(TRANSACTION_ID_1);
    expenseDetailResponseType.setAmount(EXPENSE_TRANSACTION_AMOUNT);
    expenseDetailResponseType.setCategory(TRANSACTION_CATEGORY_RESPONSE_TYPE);
    expenseDetailResponseType.setDescription(TRANSACTION_DESCRIPTION);
    return expenseDetailResponseType;
  }
}
