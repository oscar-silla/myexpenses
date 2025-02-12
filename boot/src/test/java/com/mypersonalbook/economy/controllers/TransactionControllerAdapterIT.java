package com.mypersonalbook.economy.controllers;

import static com.mypersonalbook.economy.utils.mocks.TransactionRequestBodyTypeMock.EXPENSE_TRANSACTION_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.TransactionRequestBodyTypeMock.REVENUE_TRANSACTION_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mypersonalbook.economy.Application;
import com.mypersonalbook.economy.adapters.TransactionControllerAdapter;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Transactional
@ActiveProfiles(profiles = "dev")
@Sql(
    scripts = {
      "classpath:com/mypersonalbook/economy/controllers/scripts/create_database.sql",
      "classpath:com/mypersonalbook/economy/controllers/scripts/initialize_database.sql"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerAdapterIT {
  @Autowired TransactionControllerAdapter transactionControllerAdapter;

  @Test
  void shouldCreateTransaction() {
    final ResponseEntity<Void> RESULT =
        this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.CREATED, RESULT.getStatusCode());
  }

  @Test
  void shouldFindTransactionById() {
    this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    final ResponseEntity<TransactionResponseType> RESULT =
        this.transactionControllerAdapter.getTransaction(1L);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
    assertNotNull(RESULT.getBody());
    assertEquals(1L, RESULT.getBody().getId());
    assertEquals(TRANSACTION_DATE_1, RESULT.getBody().getDate());
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getBody().getAmount());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getBody().getCategory());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getBody().getDescription());
  }

  @Test
  void shouldFindTransactions_WithExpensesAndRevenues_ForSameDay() {
    this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    this.transactionControllerAdapter.postTransaction(REVENUE_TRANSACTION_REQUEST_BODY_TYPE());
    final ResponseEntity<TransactionsResponseType> RESULT =
        this.transactionControllerAdapter.getTransactions(
            PAGE_SIZE, PAGE_NUMBER, START_DATE, END_DATE);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
    assertNotNull(RESULT.getBody());
    assertEquals(1, RESULT.getBody().getResults().size());
    assertEquals(TRANSACTION_DATE_1, RESULT.getBody().getResults().get(0).getDate());
    // AMOUNT
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getBody().getResults().get(0).getAmount().getExpense());
    assertEquals(REVENUE_TRANSACTION_AMOUNT, RESULT.getBody().getResults().get(0).getAmount().getRevenue());
    // EXPENSE
    assertEquals(1, RESULT.getBody().getResults().get(0).getExpenses().get(0).getId());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getBody().getResults().get(0).getExpenses().get(0).getCategory());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getBody().getResults().get(0).getExpenses().get(0).getDescription());
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getBody().getResults().get(0).getExpenses().get(0).getAmount());
    // REVENUE
    assertEquals(2, RESULT.getBody().getResults().get(0).getRevenues().get(0).getId());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getBody().getResults().get(0).getRevenues().get(0).getCategory());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getBody().getResults().get(0).getRevenues().get(0).getDescription());
    assertEquals(REVENUE_TRANSACTION_AMOUNT, RESULT.getBody().getResults().get(0).getRevenues().get(0).getAmount());
    // SUMMARY
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getBody().getSummary().getTotalExpense());
    assertEquals(REVENUE_TRANSACTION_AMOUNT, RESULT.getBody().getSummary().getTotalRevenue());
    assertEquals(TRANSACTION_TOTAL_MONEY, RESULT.getBody().getSummary().getTotalMoney());
    // PAGINATION
    assertEquals(PAGE_NUMBER, RESULT.getBody().getPagination().getPageNumber());
    assertEquals(1, RESULT.getBody().getPagination().getPageSize());
    assertEquals(1, RESULT.getBody().getPagination().getTotalResults());
  }

  @Test
  void shouldDeleteTransactionById() {
    this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.OK, this.transactionControllerAdapter.getTransaction(1L).getStatusCode());
    assertEquals(HttpStatus.NO_CONTENT, this.transactionControllerAdapter.deleteTransaction(1L).getStatusCode());
    assertThrows(NotFoundException.class, () -> this.transactionControllerAdapter.getTransaction(1L));
  }
}
