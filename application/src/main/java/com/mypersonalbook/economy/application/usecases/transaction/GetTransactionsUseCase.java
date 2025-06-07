package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.ports.driving.transaction.GetTransactionsUseCasePort;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.models.response.transaction.TransactionsSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.Utils.*;

@Service
public class GetTransactionsUseCase implements GetTransactionsUseCasePort {
  private final Logger log = LoggerFactory.getLogger(GetTransactionsUseCase.class);
  private final TransactionService transactionService;
  private final AuthService authService;

  public GetTransactionsUseCase(TransactionService transactionService, AuthService authService) {
    this.transactionService = transactionService;
    this.authService = authService;
  }

  @Override
  public TransactionsResponse execute(GetTransactionsQueryParams queryParams) {
    this.validateQueryParams(queryParams);
    var filter = this.buildTransactionFilter(queryParams);
    Page<Transaction> transactions = this.transactionService.find(filter);
    TransactionsSummary summary = this.transactionService.getSummary(filter);
    return new TransactionsResponse(transactions, summary);
  }

  private void validateQueryParams(GetTransactionsQueryParams queryParams) {
    validateOrThrowDateRange(queryParams.startDate(), queryParams.endDate(), log);
    validateOrThrowPagination(queryParams.pageNumber(), queryParams.pageSize(), log);
  }

  private TransactionFilter buildTransactionFilter(GetTransactionsQueryParams queryParams) {
    return new TransactionFilter(
        this.authService.getUserId(),
        queryParams.startDate() == null
            ? getFirstDayOfMonth(LocalDate.now())
            : queryParams.startDate(),
        queryParams.endDate() == null ? getLastDayOfMonth(LocalDate.now()) : queryParams.endDate(),
        new PaginationFilter(queryParams.pageNumber(), queryParams.pageSize()));
  }
}
