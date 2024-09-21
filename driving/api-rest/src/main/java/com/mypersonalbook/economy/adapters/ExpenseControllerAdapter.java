package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.mappers.ExpenseControllerMapper;
import com.mypersonalbook.economy.ports.in.*;
import openapi.economy.api.ExpensesApi;
import openapi.economy.model.ExpenseRequestBodyType;
import openapi.economy.model.ExpenseResponseType;
import openapi.economy.model.ExpensesResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/economy/v1")
public class ExpenseControllerAdapter implements ExpensesApi {
  private final Logger logger = LoggerFactory.getLogger(ExpenseControllerAdapter.class);
  private final ExpenseControllerMapper expenseControllerMapper;

  private final SaveExpenseUseCasePort saveExpenseUseCase;
  private final DeleteExpenseUseCasePort deleteExpenseUseCase;
  private final GetExpenseUseCasePort getExpenseUseCase;
  private final GetExpensesUseCasePort getExpensesUseCase;
  private final ModifyExpenseUseCasePort modifyExpenseUseCase;

  public ExpenseControllerAdapter(
      ExpenseControllerMapper expenseControllerMapper,
      SaveExpenseUseCasePort saveExpenseUseCase,
      DeleteExpenseUseCasePort deleteExpenseUseCase,
      GetExpenseUseCasePort getExpenseUseCase,
      GetExpensesUseCasePort getExpensesUseCase,
      ModifyExpenseUseCasePort modifyExpenseUseCase) {
    this.expenseControllerMapper = expenseControllerMapper;
    this.saveExpenseUseCase = saveExpenseUseCase;
    this.deleteExpenseUseCase = deleteExpenseUseCase;
    this.getExpenseUseCase = getExpenseUseCase;
    this.getExpensesUseCase = getExpensesUseCase;
    this.modifyExpenseUseCase = modifyExpenseUseCase;
  }

  @Override
  public ResponseEntity<Void> deleteExpense(Long id) {
    logger.info("DELETE /economy/v1/expenses/{id} with id: {}", id);
    this.deleteExpenseUseCase.execute(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<ExpenseResponseType> getExpense(Long id) {
    logger.info("GET /economy/v1/expenses/{id} with id: {}", id);
    Expense expense = this.getExpenseUseCase.execute(id);
    ExpenseResponseType expenseResponseType =
        this.expenseControllerMapper.toExpenseResponseType(expense);
    return ResponseEntity.status(HttpStatus.OK).body(expenseResponseType);
  }

  @Override
  public ResponseEntity<ExpensesResponseType> getExpenses(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    logger.info(
        "GET /economy/v1/expenses with pageSize: {}, pageNumber: {}, startDate: {}, endDate: {}",
        pageSize,
        pageNumber,
        startDate,
        endDate);
    Page<Expense> expensesPage =
        this.getExpensesUseCase.execute(pageSize, pageNumber, startDate, endDate);
    ExpensesResponseType expensesResponseType =
        this.expenseControllerMapper.toExpensesResponseType(expensesPage);
    return ResponseEntity.status(HttpStatus.OK).body(expensesResponseType);
  }

  @Override
  public ResponseEntity<Void> patchExpense(Long id, ExpenseRequestBodyType expenseRequestBodyType) {
    logger.info(
        "PATCH /economy/v1/expenses/{id} with id: {}, body: {}",
        id,
        expenseRequestBodyType.toString());
    Expense expense = this.expenseControllerMapper.toExpense(expenseRequestBodyType);
    expense.setId(id);
    this.modifyExpenseUseCase.execute(expense);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> postExpense(ExpenseRequestBodyType expenseRequestBodyType) {
    logger.info("POST /economy/v1/expenses with body: {}", expenseRequestBodyType.toString());
    this.saveExpenseUseCase.execute(this.expenseControllerMapper.toExpense(expenseRequestBodyType));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
