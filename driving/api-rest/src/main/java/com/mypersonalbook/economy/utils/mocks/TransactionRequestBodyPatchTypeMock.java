package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.TransactionRequestBodyPatchType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_DESCRIPTION;

public class TransactionRequestBodyPatchTypeMock {
  public static TransactionRequestBodyPatchType TRANSACTION_REQUEST_BODY_PATCH_TYPE() {
    TransactionRequestBodyPatchType transactionRequestBodyPatchType =
        new TransactionRequestBodyPatchType();
    transactionRequestBodyPatchType.setDate(TRANSACTION_DATE_1);
    transactionRequestBodyPatchType.setAmount(EXPENSE_TRANSACTION_AMOUNT);
    transactionRequestBodyPatchType.setCategory(CATEGORY_NAME_UPPER_CASE);
    transactionRequestBodyPatchType.setDescription(TRANSACTION_DESCRIPTION);
    return transactionRequestBodyPatchType;
  }
}
