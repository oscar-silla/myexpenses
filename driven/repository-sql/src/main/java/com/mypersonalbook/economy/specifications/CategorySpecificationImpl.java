package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.models.CategoryMO_;
import com.mypersonalbook.economy.models.TransactionTypeMO_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySpecificationImpl implements CategorySpecification {
  @Override
  public Specification<CategoryMO> getSpecification(CategoryFilter filter) {
    List<Specification<CategoryMO>> specifications = new ArrayList<>();
    if (filter.name() != null) {
      specifications.add(this.equalsName(filter.name()));
    }
    if (filter.type() != null) {
      specifications.add(this.equalsType(filter.type()));
    }
    return specifications.stream().reduce(Specification::and).orElse(null);
  }

  private Specification<CategoryMO> equalsName(String name) {
    return (root, query, cb) -> cb.equal(cb.upper(root.get(CategoryMO_.NAME)), name);
  }

  private Specification<CategoryMO> equalsType(String type) {
    return (root, query, cb) ->
        cb.equal(cb.upper(root.get(CategoryMO_.TYPE).get(TransactionTypeMO_.ID)), type);
  }
}
