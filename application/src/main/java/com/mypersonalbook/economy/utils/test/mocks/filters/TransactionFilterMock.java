package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.filters.TransactionFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.END_DATE;
import static com.mypersonalbook.economy.utils.test.TestConstants.START_DATE;

public class TransactionFilterMock {
  public static final TransactionFilter TRANSACTION_FILTER =
      new TransactionFilter(START_DATE, END_DATE);
}
