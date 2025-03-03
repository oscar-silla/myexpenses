package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.models.TransactionMO;
import org.springframework.data.jpa.domain.Specification;

public interface TransactionSpecification {
  Specification<TransactionMO> getSpecification(TransactionFilter filter);
}
