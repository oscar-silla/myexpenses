package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.ports.in.SaveExpenseUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
  }

  private void validate(Expense expense) {
    if (expense.amount() == null
        || !StringUtils.hasText(expense.category())
        || (expense.description() != null && expense.description().isBlank())
        || expense.date() == null) {}
  }
}
