package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.application.filters.TransactionFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.END_DATE;
import static com.mypersonalbook.economy.utils.test.TestConstants.START_DATE;
import static com.mypersonalbook.economy.utils.test.mocks.filters.PaginationFilterMock.PAGINATION_FILTER;

public class TransactionFilterMock {
  public static final TransactionFilter TRANSACTION_FILTER =
      new TransactionFilter(START_DATE, END_DATE, PAGINATION_FILTER);
}
