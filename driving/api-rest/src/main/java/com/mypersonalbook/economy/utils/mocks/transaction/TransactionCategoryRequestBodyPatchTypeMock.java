package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionCategoryRequestBodyPatchType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionCategoryRequestBodyPatchTypeMock {
  public static TransactionCategoryRequestBodyPatchType
      TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE() {
    TransactionCategoryRequestBodyPatchType transactionCategoryRequestBodyPatchType =
        new TransactionCategoryRequestBodyPatchType();
    transactionCategoryRequestBodyPatchType.setName(CATEGORY_NAME_UPPER_CASE);
    transactionCategoryRequestBodyPatchType.setColor(CATEGORY_COLOR);
    return transactionCategoryRequestBodyPatchType;
  }
  public static TransactionCategoryRequestBodyPatchType
  TRANSACTION_CATEGORY_REQUEST_BODY_PATCH_TYPE_2() {
    TransactionCategoryRequestBodyPatchType transactionCategoryRequestBodyPatchType =
            new TransactionCategoryRequestBodyPatchType();
    transactionCategoryRequestBodyPatchType.setName(CATEGORY_NAME_UPPER_CASE_2);
    transactionCategoryRequestBodyPatchType.setColor(CATEGORY_COLOR_2);
    return transactionCategoryRequestBodyPatchType;
  }
}
