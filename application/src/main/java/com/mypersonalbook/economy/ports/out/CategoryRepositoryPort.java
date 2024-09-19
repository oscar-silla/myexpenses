package com.mypersonalbook.economy.ports.out;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.filters.CategoryFilter;

import java.util.Optional;

public interface CategoryRepositoryPort {
  Optional<Category> findOne(CategoryFilter filter);
}
