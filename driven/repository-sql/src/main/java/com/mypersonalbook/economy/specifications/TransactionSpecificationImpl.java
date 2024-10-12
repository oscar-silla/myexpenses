package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.models.TransactionMO_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionSpecificationImpl implements TransactionSpecification {
  @Override
  public Specification<TransactionMO> getSpecification(TransactionFilter filter) {
    List<Specification<TransactionMO>> specifications = new ArrayList<>();
    if (filter.startDate() != null && filter.endDate() != null) {
      specifications.add(this.betweenDates(filter.startDate(), filter.endDate()));
    }
    return specifications.stream().reduce(Specification::and).orElse(null);
  }

  private Specification<TransactionMO> betweenDates(LocalDate startDate, LocalDate endDate) {
    return (root, query, cb) ->
        cb.and(
            cb.greaterThanOrEqualTo(root.get(TransactionMO_.DATE), startDate),
            cb.lessThanOrEqualTo(root.get(TransactionMO_.DATE), endDate));
  }
}
