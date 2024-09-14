package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import openapi.economy.model.ExpenseRequestBodyType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseControllerMapper {
    Expense toExpense(ExpenseRequestBodyType expenseRequestBodyType);
}
