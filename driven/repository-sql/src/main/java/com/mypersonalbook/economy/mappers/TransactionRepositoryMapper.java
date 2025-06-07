package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.models.response.transaction.TransactionsSummary;
import com.mypersonalbook.economy.projections.TransactionsSummaryProjection;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {
      CategoryRepositoryMapper.class,
      UserRepositoryMapper.class,
      TransactionTypeRepositoryMapper.class
    },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionRepositoryMapper {
  TransactionMO toTransactionMO(Transaction transaction);

  @Mapping(target = "type", source = "type.id")
  Transaction toTransaction(TransactionMO transactionMO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapFromDtoToTransaction(Transaction dto, @MappingTarget Transaction transaction);

  default TransactionsSummary toTransactionsSummary(TransactionsSummaryProjection projection) {
    return new TransactionsSummary(
        projection.getTotalRevenue(), projection.getTotalExpense(), projection.getTotalMoney());
  }
}
