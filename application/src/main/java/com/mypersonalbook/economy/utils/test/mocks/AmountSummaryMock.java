package com.mypersonalbook.economy.utils.test.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_TRANSACTION_AMOUNT;

import com.mypersonalbook.economy.domain.AmountSummary;

public class AmountSummaryMock {
    public static final AmountSummary AMOUNT_SUMMARY = new AmountSummary(EXPENSE_TRANSACTION_AMOUNT, EXPENSE_TRANSACTION_AMOUNT);
}
