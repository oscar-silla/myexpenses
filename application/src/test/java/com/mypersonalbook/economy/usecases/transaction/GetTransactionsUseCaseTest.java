package com.mypersonalbook.economy.usecases.transaction;

import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.application.usecases.transaction.GetTransactionsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionsSummaryMock.TRANSACTION_SUMMARY;
import static com.mypersonalbook.economy.utils.test.mocks.queryparams.GetTransactionQueryParamsMock.GET_TRANSACTIONS_QUERY_PARAMS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetTransactionsUseCaseTest {
  GetTransactionsUseCase getTransactionsUseCase;
  @Mock private TransactionService transactionService;
  @Mock private AuthService authService;

  @BeforeEach
  void setUp() {
    this.getTransactionsUseCase =
        new GetTransactionsUseCase(this.transactionService, this.authService);
  }

  @Test
  @DisplayName("Should return transaction response when execute")
  void shouldReturnTransactionResponse_WhenExecute() {
    when(this.transactionService.find(any(TransactionFilter.class))).thenReturn(TRANSACTION_PAGE);
    when(this.transactionService.getSummary(any(TransactionFilter.class)))
        .thenReturn(TRANSACTION_SUMMARY);
    this.getTransactionsUseCase.execute(GET_TRANSACTIONS_QUERY_PARAMS);
    verify(this.transactionService).getSummary(any(TransactionFilter.class));
  }
}
