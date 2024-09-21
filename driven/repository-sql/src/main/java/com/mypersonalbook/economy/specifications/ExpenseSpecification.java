package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.models.ExpenseMO;
import org.springframework.data.jpa.domain.Specification;

public interface ExpenseSpecification {
  Specification<ExpenseMO> getSpecification(ExpenseFilter filter);
}
