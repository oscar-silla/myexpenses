package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.ports.in.GetExpensesUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.Utils.validateAndThrowDateRange;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowPagination;

@Service
public class GetExpensesUseCase implements GetExpensesUseCasePort {
  private final ExpenseService expenseService;
  private final Logger logger = LoggerFactory.getLogger(GetExpenseUseCase.class);

  public GetExpensesUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public Page<Expense> execute(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    validateAndThrowPagination(pageNumber, pageSize, logger);
    validateAndThrowDateRange(startDate, endDate, logger);
    ExpenseFilter expenseFilter = new ExpenseFilter(startDate, endDate);
    PaginationFilter paginationFilter = new PaginationFilter(pageNumber, pageSize);
    return this.expenseService.find(expenseFilter, paginationFilter);
  }
}
