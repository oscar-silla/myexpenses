package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.TRANSACTION_PAGE;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionSummaryResponseMock.TRANSACTION_SUMMARY_RESPONSE;

public class TransactionsResponseMock {
  public static final TransactionsResponse TRANSACTIONS_RESPONSE =
      new TransactionsResponse(TRANSACTION_PAGE, TRANSACTION_SUMMARY_RESPONSE);
}
