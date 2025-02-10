package com.mypersonalbook.economy.controllers;

import com.mypersonalbook.economy.Application;
import com.mypersonalbook.economy.adapters.TransactionControllerAdapter;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.mypersonalbook.economy.utils.mocks.TransactionRequestBodyTypeMock.TRANSACTION_REQUEST_BODY_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ActiveProfiles(profiles = "dev")
@Sql(
    scripts = {
      "classpath:com/mypersonalbook/economy/controllers/scripts/create_database.sql",
      "classpath:com/mypersonalbook/economy/controllers/scripts/initialize_database.sql"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerAdapterIT {
  @Autowired
  TransactionControllerAdapter transactionControllerAdapter;

  @Test
  void shouldCreateTransaction() {
    final ResponseEntity<Void> RESULT =
        this.transactionControllerAdapter.postTransaction(TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.CREATED, RESULT.getStatusCode());
  }
}
