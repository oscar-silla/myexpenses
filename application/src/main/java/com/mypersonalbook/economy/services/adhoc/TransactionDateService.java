package com.mypersonalbook.economy.services.adhoc;

import com.mypersonalbook.economy.domain.AmountSummary;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionSummaryResponse;
import com.mypersonalbook.economy.queryparams.GetTransactionsQueryParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.mypersonalbook.economy.utils.AppConstants.EXPENSE_TYPE;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowDateRange;
import static com.mypersonalbook.economy.utils.Utils.validateAndThrowPagination;

@Service
public class TransactionDateService {
  Logger log = LoggerFactory.getLogger(TransactionDateService.class);

  public TransactionFilter validateQueryParamsAndGetFilters(
      GetTransactionsQueryParams queryParams) {
    validateAndThrowPagination(queryParams.pageNumber(), queryParams.pageSize(), log);
    validateAndThrowDateRange(queryParams.startDate(), queryParams.endDate(), log);
    var paginationFilter = new PaginationFilter(queryParams.pageNumber(), queryParams.pageSize());
    return new TransactionFilter(queryParams.startDate(), queryParams.endDate(), paginationFilter);
  }

  public Page<TransactionDateResponse> collectTransactionDateResponsePage(
      Page<Transaction> transactionsPage) {
    if (transactionsPage.getContent().isEmpty()) {
      log.info("There are not transactions with the provided dates.");
      return new PageImpl<>(List.of());
    }
    Map<LocalDate, TransactionDateResponse> transactionDateResponseMap = new HashMap<>();
    transactionsPage
        .getContent()
        .forEach(
            transaction -> {
              TransactionDateResponse transactionDateResponse =
                  transactionDateResponseMap.computeIfAbsent(
                      transaction.getDate(),
                      date ->
                          new TransactionDateResponse(
                              transaction.getDate(),
                              new ArrayList<>(),
                              new ArrayList<>(),
                              new AmountSummary()));
              if (this.isExpenseType(transaction)) {
                transactionDateResponse.expenses().add(transaction);
                transactionDateResponse
                    .amount()
                    .setExpense(
                        transactionDateResponse.amount().getExpense() + transaction.getAmount());
              } else {
                transactionDateResponse.revenues().add(transaction);
                transactionDateResponse
                    .amount()
                    .setRevenue(
                        transactionDateResponse.amount().getRevenue() + transaction.getAmount());
              }
            });
    return new PageImpl<>(getSortTransactionDateResponseList(transactionDateResponseMap));
  }

  private List<TransactionDateResponse> getSortTransactionDateResponseList(
      Map<LocalDate, TransactionDateResponse> transactionDateResponseMap) {
    return transactionDateResponseMap.values().stream()
        .sorted(Comparator.comparing(TransactionDateResponse::date).reversed())
        .map(this::sortTransactionsById)
        .toList();
  }

  private TransactionDateResponse sortTransactionsById(
      TransactionDateResponse transactionDateResponse) {
    List<Transaction> expenses =
        transactionDateResponse.expenses().stream()
            .sorted(Comparator.comparing(Transaction::getId).reversed())
            .toList();
    List<Transaction> revenues =
        transactionDateResponse.revenues().stream()
            .sorted(Comparator.comparing(Transaction::getId).reversed())
            .toList();
    return new TransactionDateResponse(
        transactionDateResponse.date(), expenses, revenues, transactionDateResponse.amount());
  }

  private boolean isExpenseType(Transaction transaction) {
    return transaction.getType().equals(EXPENSE_TYPE);
  }

  public TransactionSummaryResponse buildTransactionSummaryResponse(
      Page<TransactionDateResponse> transactionDateResponsePage) {
    List<TransactionDateResponse> transactionDates = transactionDateResponsePage.getContent();
    if (transactionDates.isEmpty()) {
      return new TransactionSummaryResponse(0f, 0f, 0f);
    } else {
      final float totalRevenue = this.sumRevenues(transactionDates);
      final float totalExpense = this.sumExpenses(transactionDates);
      final float totalMoney = totalRevenue - totalExpense;
      return new TransactionSummaryResponse(totalRevenue, totalExpense, totalMoney);
    }
  }

  private float sumRevenues(List<TransactionDateResponse> transactionDates) {
    return transactionDates.stream().reduce(0f, (a, c) -> a + c.amount().getRevenue(), Float::sum);
  }

  private float sumExpenses(List<TransactionDateResponse> transactionDates) {
    return transactionDates.stream().reduce(0f, (a, c) -> a + c.amount().getExpense(), Float::sum);
  }
}
