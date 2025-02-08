package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.LocaleName;
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

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionControllerMapper {
  @Mapping(target = "category.names", source = "category", qualifiedByName = "toLocaleNames")
  @Mapping(target = "type", source = "type", qualifiedByName = "toUpperCase")
  Transaction toExpense(TransactionRequestBodyType expenseRequestBodyType);

  @Mapping(target = "category.names", source = "category", qualifiedByName = "toLocaleNames")
  Transaction toExpense(TransactionRequestBodyPatchType expenseRequestBodyType);

  @Mapping(target = "category", source = "category.names", qualifiedByName = "toCategory")
  TransactionResponseType toExpenseResponseType(Transaction transaction);

  @Mapping(target = "category", source = "category.names", qualifiedByName = "toCategory")
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

  @Named("toLocaleNames")
  default List<LocaleName> toLocaleNames(String string) {
    return string != null
        ? new ArrayList<>(List.of(new LocaleName(string.toUpperCase(), null)))
        : null;
  }

  @Named("toCategory")
  default String toCategory(List<LocaleName> names) {
    return names.get(0).getName();
  }
}
