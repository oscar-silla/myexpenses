package com.mypersonalbook.economy.controllers;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionRequestBodyPatchTypeMock.TRANSACTION_REQUEST_BODY_PATCH_TYPE_MODIFIED;
import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionRequestBodyTypeMock.*;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import com.mypersonalbook.economy.Application;
import com.mypersonalbook.economy.adapters.TransactionControllerAdapter;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(
    scripts = {
      "classpath:com/mypersonalbook/economy/controllers/scripts/create_database.sql",
      "classpath:com/mypersonalbook/economy/controllers/scripts/initialize_database.sql"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
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
  void shouldModifyTransaction() {
    this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    this.transactionControllerAdapter.patchTransaction(
        1L,
        CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
        TRANSACTION_REQUEST_BODY_PATCH_TYPE_MODIFIED());
    final ResponseEntity<TransactionResponseType> MODIFIED_RESULT =
        this.transactionControllerAdapter.getTransaction(1L);
    assertNotNull(MODIFIED_RESULT.getBody());
    assertEquals(1L, MODIFIED_RESULT.getBody().getId());
    assertEquals(TRANSACTION_DATE_2, MODIFIED_RESULT.getBody().getDate());
    assertEquals(EXPENSE_TRANSACTION_AMOUNT_2, MODIFIED_RESULT.getBody().getAmount());
    assertEquals(CATEGORY_NAME_UPPER_CASE_2, MODIFIED_RESULT.getBody().getCategory());
    assertEquals(TRANSACTION_DESCRIPTION_2, MODIFIED_RESULT.getBody().getDescription());
  }

  @Test
  void shouldDeleteTransactionById() {
    this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(
        HttpStatus.OK, this.transactionControllerAdapter.getTransaction(1L).getStatusCode());
    assertEquals(
        HttpStatus.NO_CONTENT,
        this.transactionControllerAdapter.deleteTransaction(1L).getStatusCode());
    assertThrows(
        NotFoundException.class, () -> this.transactionControllerAdapter.getTransaction(1L));
  }
}
