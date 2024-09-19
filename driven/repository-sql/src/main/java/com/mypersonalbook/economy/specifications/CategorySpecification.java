package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.models.CategoryMO;
import org.springframework.data.jpa.domain.Specification;

public interface CategorySpecification {
  Specification<CategoryMO> getSpecification(CategoryFilter filter);
}
