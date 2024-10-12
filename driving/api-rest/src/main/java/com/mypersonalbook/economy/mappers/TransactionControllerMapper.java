package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.TransactionDateResponse;
import openapi.economy.model.PaginationResponseType;
import openapi.economy.model.TransactionDateResponseType;
import openapi.economy.model.TransactionDetailResponseType;
import openapi.economy.model.TransactionRequestBodyType;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import openapi.economy.model.TransactionRequestBodyPatchType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionControllerMapper {
  @Mapping(target = "category.name", source = "category")
  Transaction toExpense(TransactionRequestBodyType expenseRequestBodyType);

  @Mapping(target = "category.name", source = "category")
  Transaction toExpense(TransactionRequestBodyPatchType expenseRequestBodyType);

  @Mapping(target = "category", source = "category.name")
  TransactionResponseType toExpenseResponseType(Transaction transaction);

  @Mapping(target = "category", source = "category.name")
  TransactionDetailResponseType toTransactionDetailResponseType(Transaction transaction);

  List<TransactionDetailResponseType> toTransactionDetailResponseTypes(
      List<Transaction> transactions);

  List<TransactionDateResponseType> toExpenseResponseTypes(
      List<TransactionDateResponse> transactionDateResponse);

  default TransactionsResponseType toTransactionDetailResponseType(
      Page<TransactionDateResponse> expenseDateResponsePage) {
    TransactionsResponseType expensesResponseType = new TransactionsResponseType();
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageNumber(expenseDateResponsePage.getNumber());
    paginationResponseType.setPageSize(expenseDateResponsePage.getSize());
    paginationResponseType.setTotalResults((int) expenseDateResponsePage.getTotalElements());
    expensesResponseType.setResults(
        this.toExpenseResponseTypes(expenseDateResponsePage.getContent()));
    expensesResponseType.setPagination(paginationResponseType);
    return expensesResponseType;
  }
}
