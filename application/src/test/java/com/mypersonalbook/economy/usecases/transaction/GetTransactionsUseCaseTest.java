package com.mypersonalbook.economy.usecases.transaction;

import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import com.mypersonalbook.economy.application.services.PaginationService;
import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.application.services.adhoc.TransactionDateService;
import com.mypersonalbook.economy.application.usecases.transaction.GetTransactionsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static com.mypersonalbook.economy.utils.test.mocks.filters.TransactionFilterMock.TRANSACTION_FILTER;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.PaginationResponseMock.PAGINATION_RESPONSE;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionDateResponseMock.TRANSACTION_DATE_RESPONSE_PAGE;
import static com.mypersonalbook.economy.utils.test.mocks.models.response.TransactionSummaryResponseMock.TRANSACTION_SUMMARY_RESPONSE;
import static com.mypersonalbook.economy.utils.test.mocks.queryparams.GetTransactionQueryParamsMock.GET_TRANSACTIONS_QUERY_PARAMS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetTransactionsUseCaseTest {
  GetTransactionsUseCase getTransactionsUseCase;
  @Mock private TransactionService transactionService;
  @Mock private TransactionDateService transactionDateService;
  @Mock private PaginationService paginationService;

  @BeforeEach
  void setUp() {
    this.getTransactionsUseCase =
        new GetTransactionsUseCase(
            this.transactionService, this.transactionDateService, this.paginationService);
  }

  @Test
  @DisplayName("Should return transaction response when execute")
  void shouldReturnTransactionResponse_WhenExecute() {
    when(this.transactionDateService.validateQueryParamsAndGetFilters(
            any(GetTransactionsQueryParams.class)))
        .thenReturn(TRANSACTION_FILTER);
    when(this.transactionService.find(any(TransactionFilter.class)))
        .thenReturn(EXPENSE_TRANSACTIONS_PAGE);
    when(this.transactionDateService.collectTransactionDateResponsePage(any()))
        .thenReturn(TRANSACTION_DATE_RESPONSE_PAGE);
    when(this.transactionDateService.buildTransactionSummaryResponse(any()))
        .thenReturn(TRANSACTION_SUMMARY_RESPONSE);
    when(this.paginationService.buildPaginationResponse(any())).thenReturn(PAGINATION_RESPONSE);
    this.getTransactionsUseCase.execute(GET_TRANSACTIONS_QUERY_PARAMS);
    verify(this.paginationService).buildPaginationResponse(any());
  }
}
