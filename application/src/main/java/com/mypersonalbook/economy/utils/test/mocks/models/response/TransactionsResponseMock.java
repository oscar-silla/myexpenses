package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.mocks.models.response.PaginationResponseMock.PAGINATION_RESPONSE;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionDateResponseMock.TRANSACTION_DATE_RESPONSE;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionSummaryResponseMock.TRANSACTION_SUMMARY_RESPONSE;

public class TransactionsResponseMock {
  public static final TransactionsResponse TRANSACTIONS_RESPONSE =
      new TransactionsResponse(
          List.of(TRANSACTION_DATE_RESPONSE), TRANSACTION_SUMMARY_RESPONSE, PAGINATION_RESPONSE);
}
