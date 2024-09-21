package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import openapi.economy.model.ExpenseRequestBodyType;
import openapi.economy.model.ExpenseResponseType;
import openapi.economy.model.ExpensesResponseType;
import openapi.economy.model.PaginationResponseType;
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

  List<ExpenseResponseType> toExpenseResponseTypes(List<Expense> expenses);

  default ExpensesResponseType toExpensesResponseType(Page<Expense> expensesPage) {
    ExpensesResponseType expensesResponseType = new ExpensesResponseType();
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageNumber(expensesPage.getNumber());
    paginationResponseType.setPageSize(expensesPage.getSize());
    paginationResponseType.setTotalResults((int) expensesPage.getTotalElements());
    expensesResponseType.setResults(this.toExpenseResponseTypes(expensesPage.getContent()));
    expensesResponseType.setPagination(paginationResponseType);
    return expensesResponseType;
  }
}
