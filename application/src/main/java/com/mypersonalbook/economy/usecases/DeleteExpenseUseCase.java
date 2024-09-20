package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.DeleteExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.springframework.stereotype.Service;

@Service
public class DeleteExpenseUseCase implements DeleteExpenseUseCasePort {
  private final ExpenseService expenseService;

  public DeleteExpenseUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public void execute(Long id) {
    this.expenseService.deleteById(id);
  }
}
