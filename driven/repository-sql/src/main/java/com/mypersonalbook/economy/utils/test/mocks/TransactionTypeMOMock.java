package com.mypersonalbook.economy.utils.test.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE;

import com.mypersonalbook.economy.models.TransactionTypeMO;

public class TransactionTypeMOMock {
  public static final TransactionTypeMO TRANSACTION_TYPE_MO = new TransactionTypeMO(
      CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE);
}
