package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.TransactionMO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {CategoryRepositoryMapper.class, },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionRepositoryMapper {
  @Mapping(target = "type.id", source = "type")
  @Mapping(target = "category.user.id", source = "user.id")
  TransactionMO toTransactionMO(Transaction transaction);

  @Mapping(target = "type", source = "type.id")
  Transaction toTransaction(TransactionMO transactionMO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapFromDtoToTransaction(Transaction dto, @MappingTarget Transaction transaction);
}
