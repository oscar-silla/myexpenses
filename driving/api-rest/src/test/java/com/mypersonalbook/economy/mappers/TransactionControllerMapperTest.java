package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionRequestBodyTypeMock.EXPENSE_TRANSACTION_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionsResponseMock.TRANSACTIONS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerMapperTest {
  TransactionControllerMapper transactionControllerMapper;

  @BeforeEach
  void setUp() {
    this.transactionControllerMapper =
        new com.mypersonalbook.economy.mappers.TransactionControllerMapperImpl();
  }

  @Test
  @DisplayName("Should map to transaction")
  void shouldMapToTransaction() {
    final Transaction RESULT =
        this.transactionControllerMapper.toTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getCategory().getName());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
    assertEquals(CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE, RESULT.getType());
  }

  @Test
  @DisplayName("Should map to transaction response type")
  void shouldMapToTransactionResponseType() {
    final TransactionResponseType RESULT =
        this.transactionControllerMapper.toExpenseResponseType(EXPENSE_TRANSACTION_1);
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getCategory());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map to transactions response type")
  void shouldMapToExpensesResponseType() {
    final TransactionsResponseType RESULT =
        this.transactionControllerMapper.toTransactionsResponseType(TRANSACTIONS_RESPONSE);
    assertEquals(PAGE_NUMBER, RESULT.getPagination().getPageNumber());
    assertEquals(PAGE_SIZE, RESULT.getPagination().getPageSize());
    assertEquals(PAGE_TOTAL_RESULTS, RESULT.getPagination().getTotalResults());
    assertEquals(TRANSACTION_DATE_1, RESULT.getResults().get(0).getDate());
    assertEquals(TRANSACTION_ID_1, RESULT.getResults().get(0).getExpenses().get(0).getId());
    assertEquals(EXPENSE_TRANSACTION_AMOUNT, RESULT.getResults().get(0).getExpenses().get(0).getAmount());
    assertEquals(
        CATEGORY_NAME_UPPER_CASE, RESULT.getResults().get(0).getExpenses().get(0).getCategory());
    assertEquals(
        TRANSACTION_DESCRIPTION, RESULT.getResults().get(0).getExpenses().get(0).getDescription());
  }
}
