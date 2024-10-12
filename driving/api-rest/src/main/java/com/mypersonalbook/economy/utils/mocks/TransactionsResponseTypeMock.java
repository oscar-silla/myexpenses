package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.TransactionsResponseType;

import java.util.List;

import static com.mypersonalbook.economy.utils.mocks.TransactionDateResponseTypeMock.TRANSACTION_DATE_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.PaginationResponseTypeMock.PAGINATION_RESPONSE_TYPE;

public class TransactionsResponseTypeMock {
  public static TransactionsResponseType TRANSACTIONS_RESPONSE_TYPE() {
    TransactionsResponseType expensesResponseType = new TransactionsResponseType();
    expensesResponseType.setResults(List.of(TRANSACTION_DATE_RESPONSE_TYPE()));
    expensesResponseType.setPagination(PAGINATION_RESPONSE_TYPE());
    return expensesResponseType;
  }
}
