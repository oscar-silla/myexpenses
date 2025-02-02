package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import openapi.economy.model.PaginationResponseType;
import openapi.economy.model.TransactionDateResponseType;
import openapi.economy.model.TransactionDetailResponseType;
import openapi.economy.model.TransactionRequestBodyType;
import openapi.economy.model.TransactionResponseType;
import openapi.economy.model.TransactionsResponseType;
import openapi.economy.model.TransactionRequestBodyPatchType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionControllerMapper {
  @Mapping(target = "category.name", source = "category", qualifiedByName = "toUpperCase")
  @Mapping(target = "type", source = "type", qualifiedByName = "toUpperCase")
  Transaction toExpense(TransactionRequestBodyType expenseRequestBodyType);

  @Mapping(target = "category.name", source = "category", qualifiedByName = "toUpperCase")
  Transaction toExpense(TransactionRequestBodyPatchType expenseRequestBodyType);

  @Mapping(target = "category", source = "category.name")
  TransactionResponseType toExpenseResponseType(Transaction transaction);

  @Mapping(target = "category", source = "category.name")
  TransactionDetailResponseType toTransactionDetailResponseType(Transaction transaction);

  List<TransactionDetailResponseType> toTransactionDetailResponseTypes(
      List<Transaction> transactions);

  List<TransactionDateResponseType> toExpenseResponseTypes(
      List<TransactionDateResponse> transactionDateResponse);

  TransactionsResponseType toTransactionsResponseType(TransactionsResponse transactionsResponse);

  @Named("toUpperCase")
  default String toUpperCase(String string) {
    return string != null ? string.toUpperCase() : null;
  }
}
