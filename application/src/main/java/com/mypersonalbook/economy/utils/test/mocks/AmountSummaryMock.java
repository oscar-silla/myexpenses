package com.mypersonalbook.economy.utils.test.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_AMOUNT;

import com.mypersonalbook.economy.domain.AmountSummary;

public class AmountSummaryMock {
    public static final AmountSummary AMOUNT_SUMMARY = new AmountSummary(TRANSACTION_AMOUNT, TRANSACTION_AMOUNT);
}
