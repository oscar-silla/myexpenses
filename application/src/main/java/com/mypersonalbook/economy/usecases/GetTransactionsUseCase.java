package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.models.response.TransactionDateResponse;
import com.mypersonalbook.economy.ports.in.GetTransactionsUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.mypersonalbook.economy.utils.Utils.validateAndThrowDateRange;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowPagination;

@Service
public class GetTransactionsUseCase implements GetTransactionsUseCasePort {
  private final TransactionService transactionService;
  private final Logger logger = LoggerFactory.getLogger(GetTransactionsUseCase.class);

  public GetTransactionsUseCase(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public Page<TransactionDateResponse> execute(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {
    validateAndThrowPagination(pageNumber, pageSize, logger);
    validateAndThrowDateRange(startDate, endDate, logger);
    TransactionFilter transactionFilter = new TransactionFilter(startDate, endDate);
    PaginationFilter paginationFilter = new PaginationFilter(pageNumber, pageSize);
    Page<Transaction> transactionsPage =
        this.transactionService.find(transactionFilter, paginationFilter);
    return this.buildTransactionDateResponsePage(transactionsPage, startDate, endDate);
  }

  private Page<TransactionDateResponse> buildTransactionDateResponsePage(
      Page<Transaction> transactionsPage, LocalDate startDate, LocalDate endDate) {
    if (transactionsPage.getContent().isEmpty()) {
      logger.info(
          "There are not transactions to show to startDate: {} and endDate: {}",
          startDate,
          endDate);
      return new PageImpl<>(List.of());
    }
    Map<LocalDate, TransactionDateResponse> transactionDateResponseMap = new HashMap<>();
    transactionsPage
        .getContent()
        .forEach(
            transaction ->
                transactionDateResponseMap
                    .computeIfAbsent(
                        transaction.getDate(),
                        date ->
                            new TransactionDateResponse(transaction.getDate(), new ArrayList<>()))
                    .getExpenses()
                    .add(transaction));
    return new PageImpl<>(getSortTransactionDateResponseList(transactionDateResponseMap));
  }

  private List<TransactionDateResponse> getSortTransactionDateResponseList(
      Map<LocalDate, TransactionDateResponse> transactionDateResponseMap) {
    return transactionDateResponseMap.values().stream()
        .sorted(Comparator.comparing(TransactionDateResponse::getDate).reversed())
        .map(this::sortExpensesById)
        .toList();
  }

  private TransactionDateResponse sortExpensesById(
      TransactionDateResponse transactionDateResponse) {
    List<Transaction> expenses =
        transactionDateResponse.getExpenses().stream()
            .sorted(Comparator.comparing(Transaction::getId).reversed())
            .toList();
    return new TransactionDateResponse(transactionDateResponse.getDate(), expenses);
  }
}
