package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.models.ExpenseMO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = CategoryRepositoryMapper.class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExpenseRepositoryMapper {
  ExpenseMO toExpenseMO(Expense expense);

  Expense toExpense(ExpenseMO expenseMO);
}
