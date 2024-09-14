package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.ExpenseRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class ExpenseRequestBodyTypeMock {
    public static ExpenseRequestBodyType EXPENSE_REQUEST_BODY_TYPE() {
        ExpenseRequestBodyType expenseRequestBodyType = new ExpenseRequestBodyType();
        expenseRequestBodyType.setDate(EXPENSE_DATE);
        expenseRequestBodyType.setAmount(EXPENSE_AMOUNT);
        expenseRequestBodyType.setCategory(CATEGORY_NAME);
        expenseRequestBodyType.setDescription(EXPENSE_DESCRIPTION);
        return expenseRequestBodyType;
    }
}
