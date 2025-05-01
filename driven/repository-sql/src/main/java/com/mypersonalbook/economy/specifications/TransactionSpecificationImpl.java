package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.models.TransactionMO_;
import com.mypersonalbook.economy.models.UserMO_;
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
    if (filter.userId() != null) {
      specifications.add(this.equalsUserId(filter.userId()));
    }
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

  private Specification<TransactionMO> equalsUserId(Long userId) {
    return (root, query, cb) -> cb.equal(root.get(TransactionMO_.USER).get(UserMO_.ID), userId);
  }
}
