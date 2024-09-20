package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.ports.in.GetExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.springframework.stereotype.Service;

@Service
public class GetExpenseUseCase implements GetExpenseUseCasePort {
  private final ExpenseService expenseService;

  public GetExpenseUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public Expense execute(Long id) {
    return this.expenseService.findById(id);
  }
}
