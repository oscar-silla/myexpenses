package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import openapi.economy.model.ExpenseRequestBodyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseControllerMapper {
    @Mapping(target = "category.name", source = "category")
    Expense toExpense(ExpenseRequestBodyType expenseRequestBodyType);
}
