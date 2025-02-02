package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.transaction.TransactionSummaryResponse;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionSummaryResponseMock {
  public static final TransactionSummaryResponse TRANSACTION_SUMMARY_RESPONSE =
      new TransactionSummaryResponse(TOTAL_REVENUE, TOTAL_EXPENSE, TOTAL_MONEY);
}
