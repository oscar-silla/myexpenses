package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.ports.driving.transaction.GetTransactionsUseCasePort;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import com.mypersonalbook.economy.application.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.mypersonalbook.economy.utils.Utils.validateAndThrowDateRange;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowPagination;

@Service
public class GetTransactionsUseCase implements GetTransactionsUseCasePort {
  private final TransactionService transactionService;
  private final AuthService authService;
  private static final Logger log = LoggerFactory.getLogger(GetTransactionsUseCase.class);

  public GetTransactionsUseCase(TransactionService transactionService, AuthService authService) {
    this.transactionService = transactionService;
    this.authService = authService;
  }

  @Override
  public Page<Transaction> execute(GetTransactionsQueryParams queryParams) {
    return this.transactionService.find(
        this.validateQueryParamsAndGetFilters(queryParams, this.authService.getUserId()));
  }

  public TransactionFilter validateQueryParamsAndGetFilters(
      GetTransactionsQueryParams queryParams, Long userId) {
    validateAndThrowPagination(queryParams.pageNumber(), queryParams.pageSize(), log);
    validateAndThrowDateRange(queryParams.startDate(), queryParams.endDate(), log);
    var paginationFilter = new PaginationFilter(queryParams.pageNumber(), queryParams.pageSize());
    return new TransactionFilter(
        userId, queryParams.startDate(), queryParams.endDate(), paginationFilter);
  }
}
