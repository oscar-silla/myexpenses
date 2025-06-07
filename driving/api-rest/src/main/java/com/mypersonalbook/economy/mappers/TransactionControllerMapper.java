package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionsSummary;
import openapi.economy.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionControllerMapper {
  @Mapping(target = "category.name", source = "category.name", qualifiedByName = "toUpperCase")
  @Mapping(target = "type", source = "type", qualifiedByName = "toUpperCase")
  @Mapping(target = "user", expression = "java(new com.mypersonalbook.economy.domain.User())")
  Transaction toTransaction(TransactionRequestBodyType expenseRequestBodyType);

  @Mapping(target = "category.name", source = "category.name", qualifiedByName = "toUpperCase")
  Transaction toTransaction(TransactionRequestBodyPatchType expenseRequestBodyType);

  TransactionResponseType toExpenseResponseType(Transaction transaction);

  TransactionDetailResponseType toTransactionDetailResponseType(Transaction transaction);

  List<TransactionDetailResponseType> toTransactionDetailResponseTypes(
      List<Transaction> transactions);

  SummaryResponseType toTransactionsSummaryResponseType(TransactionsSummary transactionsSummary);

  default TransactionsResponseType toTransactionsResponseType(
      TransactionsResponse transactionsResponse) {
    TransactionsResponseType transactionsResponseType = new TransactionsResponseType();
    transactionsResponseType.setResults(
        this.toTransactionDetailResponseTypes(transactionsResponse.results().getContent()));
    transactionsResponseType.setSummary(
        this.toTransactionsSummaryResponseType(transactionsResponse.summary()));
    transactionsResponseType.setPagination(
        this.toPaginationResponseType(transactionsResponse.results()));
    return transactionsResponseType;
  }

  default PaginationResponseType toPaginationResponseType(Page<Transaction> transactionPage) {
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageSize(transactionPage.getSize());
    paginationResponseType.setPageNumber(transactionPage.getNumber() + 1);
    paginationResponseType.setRetrievedResults(transactionPage.getContent().size());
    paginationResponseType.setTotalResults((int) transactionPage.getTotalElements());
    return paginationResponseType;
  }

  @Named("toUpperCase")
  default String toUpperCase(String string) {
    return string != null ? string.toUpperCase() : null;
  }
}
