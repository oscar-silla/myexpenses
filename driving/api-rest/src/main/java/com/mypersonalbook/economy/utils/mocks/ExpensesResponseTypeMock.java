package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.ExpensesResponseType;

import java.util.List;

import static com.mypersonalbook.economy.utils.mocks.ExpenseDateResponseTypeMock.EXPENSE_DATE_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.ExpenseResponseTypeMock.EXPENSE_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.PaginationResponseTypeMock.PAGINATION_RESPONSE_TYPE;

public class ExpensesResponseTypeMock {
  public static ExpensesResponseType EXPENSES_RESPONSE_TYPE() {
    ExpensesResponseType expensesResponseType = new ExpensesResponseType();
    expensesResponseType.setResults(List.of(EXPENSE_DATE_RESPONSE_TYPE()));
    expensesResponseType.setPagination(PAGINATION_RESPONSE_TYPE());
    return expensesResponseType;
  }
}
