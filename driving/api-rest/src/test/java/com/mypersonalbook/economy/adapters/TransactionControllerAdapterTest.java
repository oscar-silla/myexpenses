package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.transaction.*;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.mappers.TransactionControllerMapper;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import openapi.economy.model.TransactionRequestBodyPatchType;
import openapi.economy.model.TransactionRequestBodyType;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionRequestBodyPatchTypeMock.TRANSACTION_REQUEST_BODY_PATCH_TYPE;
import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionRequestBodyTypeMock.EXPENSE_TRANSACTION_REQUEST_BODY_TYPE;
import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionResponseTypeMock.TRANSACTION_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.mocks.transaction.TransactionsResponseTypeMock.TRANSACTIONS_RESPONSE_TYPE;
import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionsResponseMock.TRANSACTIONS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerAdapterTest {
  TransactionControllerAdapter transactionControllerAdapter;

  @Mock private TransactionControllerMapper transactionControllerMapper;
  @Mock private SaveTransactionUseCasePort saveExpenseUseCase;
  @Mock private DeleteTransactionUseCasePort deleteExpenseUseCase;
  @Mock private GetTransactionUseCasePort getExpenseUseCase;
  @Mock private GetTransactionsUseCasePort getExpensesUseCase;
  @Mock private ModifyTransactionUseCasePort modifyExpenseUseCase;

  @BeforeEach
  void setUp() {
    this.transactionControllerAdapter =
        new TransactionControllerAdapter(
            this.transactionControllerMapper,
            this.saveExpenseUseCase,
            this.deleteExpenseUseCase,
            this.getExpenseUseCase,
            this.getExpensesUseCase,
            this.modifyExpenseUseCase);
  }

  @Test
  @DisplayName("Should return 200 status code when post expense")
  void shouldReturn200StatusCode_WhenPostExpense() {
    when(this.transactionControllerMapper.toTransaction(any(TransactionRequestBodyType.class)))
        .thenReturn(EXPENSE_TRANSACTION_1);
    doNothing().when(this.saveExpenseUseCase).execute(any(Transaction.class));
    final ResponseEntity<Void> RESULT =
        this.transactionControllerAdapter.postTransaction(EXPENSE_TRANSACTION_REQUEST_BODY_TYPE());
    assertEquals(HttpStatus.CREATED, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 204 status code when delete expense")
  void shouldReturn204StatusCode_WhenDeleteExpense() {
    doNothing().when(this.deleteExpenseUseCase).execute(anyLong());
    final ResponseEntity<Void> RESULT =
        this.transactionControllerAdapter.deleteTransaction(TRANSACTION_ID_1);
    assertEquals(HttpStatus.NO_CONTENT, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when get expense")
  void shouldReturn200StatusCode_WhenGetExpense() {
    when(this.getExpenseUseCase.execute(anyLong())).thenReturn(EXPENSE_TRANSACTION_1);
    when(this.transactionControllerMapper.toExpenseResponseType(any(Transaction.class)))
        .thenReturn(TRANSACTION_RESPONSE_TYPE());
    final ResponseEntity<TransactionResponseType> RESULT =
        this.transactionControllerAdapter.getTransaction(TRANSACTION_ID_1);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when get expenses")
  void shouldReturn200StatusCode_WhenGetExpenses() {
    when(this.getExpensesUseCase.execute(any(GetTransactionsQueryParams.class)))
        .thenReturn(TRANSACTIONS_RESPONSE);
    when(this.transactionControllerMapper.toTransactionsResponseType(
            any(TransactionsResponse.class)))
        .thenReturn(TRANSACTIONS_RESPONSE_TYPE());
    final ResponseEntity<TransactionsResponseType> RESULT =
        this.transactionControllerAdapter.getTransactions(
            PAGE_NUMBER, PAGE_SIZE, START_DATE, END_DATE);
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }

  @Test
  @DisplayName("Should return 200 status code when patch expense")
  void shouldReturn200StatusCode_WhenPatchExpense() {
    when(this.transactionControllerMapper.toTransaction(any(TransactionRequestBodyPatchType.class)))
        .thenReturn(EXPENSE_TRANSACTION_1);
    doNothing().when(this.modifyExpenseUseCase).execute(any(Transaction.class));
    final ResponseEntity<Void> RESULT =
        this.transactionControllerAdapter.patchTransaction(
            TRANSACTION_ID_1,
            CATEGORY_EXPENSE_TRANSACTION_TYPE_LOWER_CASE,
            TRANSACTION_REQUEST_BODY_PATCH_TYPE());
    assertEquals(HttpStatus.OK, RESULT.getStatusCode());
  }
}
