package com.mypersonalbook.economy.utils.test.mocks.queryparams;

import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class GetTransactionQueryParamsMock {
  public static final GetTransactionsQueryParams GET_TRANSACTIONS_QUERY_PARAMS =
      new GetTransactionsQueryParams(PAGE_SIZE, PAGE_NUMBER, START_DATE, END_DATE);
  public static final GetTransactionsQueryParams GET_TRANSACTIONS_QUERY_PARAMS_WITH_WRONG_DATES =
          new GetTransactionsQueryParams(PAGE_SIZE, PAGE_NUMBER, END_DATE, START_DATE);
}
