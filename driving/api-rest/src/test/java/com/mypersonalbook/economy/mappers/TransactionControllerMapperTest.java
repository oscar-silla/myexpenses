package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.mocks.TransactionRequestBodyTypeMock.TRANSACTION_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionDateResponseMock.TRANSACTION_DATE_RESPONSE_PAGE;
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
  void shouldMapToExpense() {
    final Transaction RESULT =
        this.transactionControllerMapper.toExpense(TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
    assertEquals(CATEGORY_TRANSACTION_TYPE, RESULT.getType());
  }

  @Test
  @DisplayName("Should map to transaction response type")
  void shouldMapToExpenseResponseType() {
    final TransactionResponseType RESULT =
        this.transactionControllerMapper.toExpenseResponseType(TRANSACTION_1);
    assertEquals(TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(CATEGORY_NAME, RESULT.getCategory());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map to transactions response type")
  void shouldMapToExpensesResponseType() {
    final TransactionsResponseType RESULT =
        this.transactionControllerMapper.toTransactionDetailResponseType(
            TRANSACTION_DATE_RESPONSE_PAGE);
    assertEquals(0, RESULT.getPagination().getPageNumber());
    assertEquals(1, RESULT.getPagination().getPageSize());
    assertEquals(1, RESULT.getPagination().getTotalResults());
    assertEquals(TRANSACTION_DATE_1, RESULT.getResults().get(0).getDate());
    assertEquals(TRANSACTION_ID_1, RESULT.getResults().get(0).getExpenses().get(0).getId());
    assertEquals(TRANSACTION_AMOUNT, RESULT.getResults().get(0).getExpenses().get(0).getAmount());
    assertEquals(CATEGORY_NAME, RESULT.getResults().get(0).getExpenses().get(0).getCategory());
    assertEquals(
        TRANSACTION_DESCRIPTION, RESULT.getResults().get(0).getExpenses().get(0).getDescription());
  }
}
