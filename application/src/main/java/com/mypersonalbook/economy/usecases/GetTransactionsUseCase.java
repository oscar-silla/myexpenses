package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.models.response.pagination.PaginationResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionSummaryResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.ports.in.GetTransactionsUseCasePort;
import com.mypersonalbook.economy.queryparams.GetTransactionsQueryParams;
import com.mypersonalbook.economy.services.PaginationService;
import com.mypersonalbook.economy.services.TransactionService;
import com.mypersonalbook.economy.services.adhoc.TransactionDateService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionsUseCase implements GetTransactionsUseCasePort {
  private final TransactionService transactionService;
  private final TransactionDateService transactionDateService;
  private final PaginationService paginationService;

  public GetTransactionsUseCase(
      TransactionService transactionService,
      TransactionDateService transactionDateService,
      PaginationService paginationService) {
    this.transactionService = transactionService;
    this.transactionDateService = transactionDateService;
    this.paginationService = paginationService;
  }

  @Override
  public TransactionsResponse execute(GetTransactionsQueryParams queryParams) {
    var filters = this.transactionDateService.validateQueryParamsAndGetFilters(queryParams);
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
