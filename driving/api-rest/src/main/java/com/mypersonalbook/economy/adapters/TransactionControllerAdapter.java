package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.transaction.*;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.mappers.TransactionControllerMapper;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;

import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import openapi.economy.api.TransactionsApi;
import openapi.economy.model.TransactionRequestBodyPatchType;
import openapi.economy.model.TransactionRequestBodyType;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/economy/v1")
public class TransactionControllerAdapter implements TransactionsApi {
  private final Logger logger = LoggerFactory.getLogger(TransactionControllerAdapter.class);
  private final TransactionControllerMapper transactionControllerMapper;

  private final SaveTransactionUseCasePort saveExpenseUseCase;
  private final DeleteTransactionUseCasePort deleteExpenseUseCase;
  private final GetTransactionUseCasePort getTransactionUseCase;
  private final GetTransactionsUseCasePort getExpensesUseCase;
  private final ModifyTransactionUseCasePort modifyExpenseUseCase;

  public TransactionControllerAdapter(
      TransactionControllerMapper transactionControllerMapper,
      SaveTransactionUseCasePort saveExpenseUseCase,
      DeleteTransactionUseCasePort deleteExpenseUseCase,
      GetTransactionUseCasePort getTransactionUseCase,
      GetTransactionsUseCasePort getExpensesUseCase,
      ModifyTransactionUseCasePort modifyExpenseUseCase) {
    this.transactionControllerMapper = transactionControllerMapper;
    this.saveExpenseUseCase = saveExpenseUseCase;
    this.deleteExpenseUseCase = deleteExpenseUseCase;
    this.getTransactionUseCase = getTransactionUseCase;
    this.getExpensesUseCase = getExpensesUseCase;
    this.modifyExpenseUseCase = modifyExpenseUseCase;
  }

  @Override
  public ResponseEntity<Void> deleteTransaction(Long id) {
    logger.info("DELETE /economy/v1/transactions/{id} with id: {}", id);
    this.deleteExpenseUseCase.execute(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<TransactionResponseType> getTransaction(Long id) {
    logger.info("GET /economy/v1/transactions/{id} with id: {}", id);
    Transaction transaction = this.getTransactionUseCase.execute(id);
    TransactionResponseType transactionResponseType =
        this.transactionControllerMapper.toExpenseResponseType(transaction);
    return ResponseEntity.status(HttpStatus.OK).body(transactionResponseType);
  }

  @Override
  public ResponseEntity<TransactionsResponseType> getTransactions(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    logger.info(
        "GET /economy/v1/expenses with pageSize: {}, pageNumber: {}, startDate: {}, endDate: {}",
        pageSize,
        pageNumber,
        startDate,
        endDate);
    TransactionsResponse transactionsResponse =
        this.getExpensesUseCase.execute(
            new GetTransactionsQueryParams(pageSize, pageNumber, startDate, endDate));
    TransactionsResponseType expensesResponseType =
        this.transactionControllerMapper.toTransactionsResponseType(transactionsResponse);
    return ResponseEntity.status(HttpStatus.OK).body(expensesResponseType);
  }

  @Override
  public ResponseEntity<Void> patchTransaction(
      Long id, String type, TransactionRequestBodyPatchType expenseRequestBodyType) {
    logger.info(
        "PATCH /economy/v1/expenses/{id} with id: {}, body: {}",
        id,
        expenseRequestBodyType.toString());
    Transaction transaction = this.transactionControllerMapper.toTransaction(expenseRequestBodyType);
    transaction.setId(id);
    transaction.getCategory().setType(type);
    transaction.setType(type);
    this.modifyExpenseUseCase.execute(transaction);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Void> postTransaction(TransactionRequestBodyType expenseRequestBodyType) {
    logger.info("POST /economy/v1/expenses with body: {}", expenseRequestBodyType.toString());
    this.saveExpenseUseCase.execute(
        this.transactionControllerMapper.toTransaction(expenseRequestBodyType));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
