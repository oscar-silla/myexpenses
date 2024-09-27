package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.models.response.ExpenseDateResponse;
import openapi.economy.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseControllerMapper {
  @Mapping(target = "category.name", source = "category")
  Expense toExpense(ExpenseRequestBodyType expenseRequestBodyType);

  @Mapping(target = "category", source = "category.name")
  ExpenseResponseType toExpenseResponseType(Expense expense);

  @Mapping(target = "category", source = "category.name")
  ExpenseDetailResponseType toExpenseDateResponseType(Expense expense);

  List<ExpenseDetailResponseType> toExpenseDetailResponseTypes(List<Expense> expenses);

  List<ExpenseDateResponseType> toExpenseResponseTypes(List<ExpenseDateResponse> expenseDateResponses);

  default ExpensesResponseType toExpenseDateResponseType(Page<ExpenseDateResponse> expenseDateResponsePage) {
    ExpensesResponseType expensesResponseType = new ExpensesResponseType();
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageNumber(expenseDateResponsePage.getNumber());
    paginationResponseType.setPageSize(expenseDateResponsePage.getSize());
    paginationResponseType.setTotalResults((int) expenseDateResponsePage.getTotalElements());
    expensesResponseType.setResults(this.toExpenseResponseTypes(expenseDateResponsePage.getContent()));
    expensesResponseType.setPagination(paginationResponseType);
    return expensesResponseType;
  }
}
