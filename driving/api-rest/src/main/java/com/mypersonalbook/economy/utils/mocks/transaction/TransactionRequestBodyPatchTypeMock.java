package com.mypersonalbook.economy.utils.mocks.transaction;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionCategoryRequestBodyPatchTypeMock.TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE;
import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionCategoryRequestBodyPatchTypeMock.TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE_2;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_DESCRIPTION;

import openapi.economy.model.TransactionRequestBodyPatchType;

public class TransactionRequestBodyPatchTypeMock {
  public static TransactionRequestBodyPatchType TRANSACTION_REQUEST_BODY_PATCH_TYPE() {
    TransactionRequestBodyPatchType transactionRequestBodyPatchType =
        new TransactionRequestBodyPatchType();
    transactionRequestBodyPatchType.setDate(TRANSACTION_DATE_1);
    transactionRequestBodyPatchType.setAmount(EXPENSE_TRANSACTION_AMOUNT);
    transactionRequestBodyPatchType.setCategory(TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE());
    transactionRequestBodyPatchType.setDescription(TRANSACTION_DESCRIPTION);
    return transactionRequestBodyPatchType;
  }

  public static TransactionRequestBodyPatchType TRANSACTION_REQUEST_BODY_PATCH_TYPE_MODIFIED() {
    TransactionRequestBodyPatchType expenseRequestBodyType = new TransactionRequestBodyPatchType();
    expenseRequestBodyType.setDate(TRANSACTION_DATE_2);
    expenseRequestBodyType.setAmount(EXPENSE_TRANSACTION_AMOUNT_2);
    expenseRequestBodyType.setCategory(TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE_2());
    expenseRequestBodyType.setDescription(TRANSACTION_DESCRIPTION_2);
    return expenseRequestBodyType;
  }
}
