package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.mappers.ExpenseControllerMapper;
import com.mypersonalbook.economy.ports.in.DeleteExpenseUseCasePort;
import com.mypersonalbook.economy.ports.in.SaveExpenseUseCasePort;
import openapi.economy.api.ExpensesApi;
import openapi.economy.model.ExpenseRequestBodyType;
import openapi.economy.model.ExpenseResponseType;
import openapi.economy.model.ExpensesResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/economy/v1")
public class ExpenseControllerAdapter implements ExpensesApi {
  private final Logger logger = LoggerFactory.getLogger(ExpenseControllerAdapter.class);
  private final ExpenseControllerMapper mapper;

  private final SaveExpenseUseCasePort saveExpenseUseCase;
  private final DeleteExpenseUseCasePort deleteExpenseUseCase;

  public ExpenseControllerAdapter(
      ExpenseControllerMapper mapper,
      SaveExpenseUseCasePort saveExpenseUseCase,
      DeleteExpenseUseCasePort deleteExpenseUseCase) {
    this.mapper = mapper;
    this.saveExpenseUseCase = saveExpenseUseCase;
    this.deleteExpenseUseCase = deleteExpenseUseCase;
  }

  @Override
  public ResponseEntity<Void> deleteExpense(Long id) {
    logger.info("DELETE /economy/v1/expenses with id: {}", id);
    this.deleteExpenseUseCase.execute(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<ExpenseResponseType> getExpense(Long id) {
    return null;
  }

  @Override
  public ResponseEntity<ExpensesResponseType> getExpenses(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public ResponseEntity<Void> patchExpense(Long id, ExpenseRequestBodyType expenseRequestBodyType) {
    return null;
  }

  @Override
  public ResponseEntity<Void> postExpense(ExpenseRequestBodyType expenseRequestBodyType) {
    logger.info("POST /economy/v1/expenses with body: {}", expenseRequestBodyType.toString());
    this.saveExpenseUseCase.execute(this.mapper.toExpense(expenseRequestBodyType));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
