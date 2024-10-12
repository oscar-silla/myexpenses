package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.TransactionTypeMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_TRANSACTION_TYPE;

public class TransactionTypeMOMock {
  public static final TransactionTypeMO TRANSACTION_TYPE_MO =
      new TransactionTypeMO(CATEGORY_TRANSACTION_TYPE);
}
