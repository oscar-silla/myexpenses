package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.models.TransactionTypeMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionTypeRepositoryMapper {
  default TransactionTypeMO toTransactionTypeMO(String type) {
    if (type != null) {
      return new TransactionTypeMO(type);
    } else {
      return null;
    }
  }
}
