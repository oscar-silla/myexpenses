package com.mypersonalbook.economy.utils.mocks.transaction;

import openapi.economy.model.TransactionDateResponseType;

import java.util.List;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionDetailResponseTypeMock.TRANSACTION_DETAIL_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_DATE_1;

public class TransactionDateResponseTypeMock {
  public static TransactionDateResponseType TRANSACTION_DATE_RESPONSE_TYPE() {
    TransactionDateResponseType expenseDateResponse = new TransactionDateResponseType();
    expenseDateResponse.setDate(TRANSACTION_DATE_1);
    expenseDateResponse.setExpenses(List.of(TRANSACTION_DETAIL_RESPONSE_TYPE()));
    return expenseDateResponse;
  }
}
