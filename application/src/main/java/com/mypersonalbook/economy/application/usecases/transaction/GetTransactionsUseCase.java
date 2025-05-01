package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.models.response.pagination.PaginationResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionSummaryResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.ports.driving.transaction.GetTransactionsUseCasePort;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import com.mypersonalbook.economy.application.services.PaginationService;
import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.application.services.adhoc.TransactionDateService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionsUseCase implements GetTransactionsUseCasePort {
  private final TransactionService transactionService;
  private final TransactionDateService transactionDateService;
  private final PaginationService paginationService;
  private final AuthService authService;

  public GetTransactionsUseCase(
      TransactionService transactionService,
      TransactionDateService transactionDateService,
      PaginationService paginationService,
      AuthService authService) {
    this.transactionService = transactionService;
    this.transactionDateService = transactionDateService;
    this.paginationService = paginationService;
    this.authService = authService;
  }

  @Override
  public TransactionsResponse execute(GetTransactionsQueryParams queryParams) {
    var filters =
        this.transactionDateService.validateQueryParamsAndGetFilters(
            queryParams, this.authService.getUserId());
    var transactions = this.transactionService.find(filters);
    var results = this.transactionDateService.collectTransactionDateResponsePage(transactions);
    var summary = this.transactionDateService.buildTransactionSummaryResponse(results);
    var pagination = this.paginationService.buildPaginationResponse(results);
    return this.buildTransactionsResponse(results, summary, pagination);
  }

  private TransactionsResponse buildTransactionsResponse(
      Page<TransactionDateResponse> results,
      TransactionSummaryResponse summary,
      PaginationResponse pagination) {
    return new TransactionsResponse(results.getContent(), summary, pagination);
  }
}
