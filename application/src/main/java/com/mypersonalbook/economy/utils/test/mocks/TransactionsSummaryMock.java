package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.response.transaction.TransactionsSummary;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class TransactionsSummaryMock {
  public static final TransactionsSummary TRANSACTION_SUMMARY =
      new TransactionsSummary(TOTAL_REVENUE, TOTAL_EXPENSE, TOTAL_MONEY);
}
