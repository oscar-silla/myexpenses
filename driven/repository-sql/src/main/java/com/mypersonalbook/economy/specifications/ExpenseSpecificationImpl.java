package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.models.ExpenseMO;
import com.mypersonalbook.economy.models.ExpenseMO_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseSpecificationImpl implements ExpenseSpecification {
  @Override
  public Specification<ExpenseMO> getSpecification(ExpenseFilter filter) {
    List<Specification<ExpenseMO>> specifications = new ArrayList<>();
    if (filter.startDate() != null && filter.endDate() != null) {
      specifications.add(this.betweenDates(filter.startDate(), filter.endDate()));
    }
    return specifications.stream().reduce(Specification::and).orElse(null);
  }

  private Specification<ExpenseMO> betweenDates(LocalDate startDate, LocalDate endDate) {
    return (root, query, cb) ->
        cb.and(
            cb.greaterThanOrEqualTo(root.get(ExpenseMO_.DATE), startDate),
            cb.lessThanOrEqualTo(root.get(ExpenseMO_.DATE), endDate));
  }
}
