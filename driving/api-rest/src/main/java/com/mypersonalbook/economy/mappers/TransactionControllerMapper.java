package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
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

  List<TransactionDateResponseType> toExpenseResponseTypes(
      List<TransactionDateResponse> transactionDateResponse);

  TransactionsResponseType toTransactionsResponseType(Page<Transaction> transactionsResponse);

  @Named("toUpperCase")
  default String toUpperCase(String string) {
    return string != null ? string.toUpperCase() : null;
  }
}
