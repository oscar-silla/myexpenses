package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.application.filters.TransactionFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.filters.PaginationFilterMock.PAGINATION_FILTER;

public class TransactionFilterMock {
  public static final TransactionFilter TRANSACTION_FILTER =
      new TransactionFilter(USER_ID, START_DATE, END_DATE, PAGINATION_FILTER);
}
