package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.models.response.ExpenseDateResponse;
import com.mypersonalbook.economy.ports.in.GetExpensesUseCasePort;
import com.mypersonalbook.economy.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.mypersonalbook.economy.utils.Utils.validateAndThrowDateRange;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowPagination;

@Service
public class GetExpensesUseCase implements GetExpensesUseCasePort {
  private final ExpenseService expenseService;
  private final Logger logger = LoggerFactory.getLogger(GetExpensesUseCase.class);

  public GetExpensesUseCase(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @Override
  public Page<ExpenseDateResponse> execute(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    validateAndThrowPagination(pageNumber, pageSize, logger);
    validateAndThrowDateRange(startDate, endDate, logger);
    ExpenseFilter expenseFilter = new ExpenseFilter(startDate, endDate);
    PaginationFilter paginationFilter = new PaginationFilter(pageNumber, pageSize);
    Page<Expense> expensePage = this.expenseService.find(expenseFilter, paginationFilter);
    return this.buildExpenseDateResponsePage(expensePage, startDate, endDate);
  }

  private Page<ExpenseDateResponse> buildExpenseDateResponsePage(
      Page<Expense> expensePage, LocalDate startDate, LocalDate endDate) {
    if (expensePage.getContent().isEmpty()) {
      logger.info(
          "There are not expenses to show to startDate: {} and endDate: {}", startDate, endDate);
      return new PageImpl<>(List.of());
    }
    Map<LocalDate, ExpenseDateResponse> expenseDateResponseMap = new HashMap<>();
    expensePage
        .getContent()
        .forEach(
            expense ->
                expenseDateResponseMap
                    .computeIfAbsent(
                        expense.getDate(),
                        date -> new ExpenseDateResponse(expense.getDate(), new ArrayList<>()))
                    .getExpenses()
                    .add(expense));
    return new PageImpl<>(getSortExpenseDateResponseList(expenseDateResponseMap));
  }

  private List<ExpenseDateResponse> getSortExpenseDateResponseList(
      Map<LocalDate, ExpenseDateResponse> expenseDateResponseMap) {
    return expenseDateResponseMap.values().stream()
        .sorted(Comparator.comparing(ExpenseDateResponse::getDate).reversed())
        .map(this::sortExpenseDateResponseById)
        .toList();
  }

  private ExpenseDateResponse sortExpenseDateResponseById(ExpenseDateResponse expenseDateResponse) {
    List<Expense> sortExpenses =
        expenseDateResponse.getExpenses().stream()
            .sorted(Comparator.comparing(Expense::getId).reversed())
            .toList();
    return new ExpenseDateResponse(expenseDateResponse.getDate(), sortExpenses);
  }
}
