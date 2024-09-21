package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.ports.in.SaveExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SaveExpenseUseCase implements SaveExpenseUseCasePort {
  private final ExpenseService expenseService;

  public SaveExpenseUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public void execute(Expense expense) {
    this.validate(expense);
    this.expenseService.save(expense);
  }

  private void validate(Expense expense) {
    if (expense.getAmount() == null
        || expense.getCategory() == null
        || !StringUtils.hasText(expense.getCategory().getName())
        || (expense.getDescription() != null && expense.getDescription().isBlank())
        || expense.getDate() == null) {
      throw new BadRequestException();
    }
  }
}
