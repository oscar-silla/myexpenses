package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.ports.in.ModifyExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.springframework.stereotype.Service;

@Service
public class ModifyExpenseUseCase implements ModifyExpenseUseCasePort {
  private final ExpenseService expenseService;

  public ModifyExpenseUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public void execute(Expense expense) {
    this.expenseService.modify(expense);
  }
}
