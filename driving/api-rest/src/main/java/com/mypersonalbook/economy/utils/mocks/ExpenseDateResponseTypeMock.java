package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.ExpenseDateResponseType;

import java.util.List;

import static com.mypersonalbook.economy.utils.mocks.ExpenseDetailResponseTypeMock.EXPENSE_DETAIL_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_DATE_1;

public class ExpenseDateResponseTypeMock {
    public static ExpenseDateResponseType EXPENSE_DATE_RESPONSE_TYPE() {
        ExpenseDateResponseType expenseDateResponse = new ExpenseDateResponseType();
        expenseDateResponse.setDate(EXPENSE_DATE_1);
        expenseDateResponse.setExpenses(List.of(EXPENSE_DETAIL_RESPONSE_TYPE()));
        return expenseDateResponse;
    }
}
