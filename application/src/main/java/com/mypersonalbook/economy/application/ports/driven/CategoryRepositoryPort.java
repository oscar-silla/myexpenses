package com.mypersonalbook.economy.application.ports.driven;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.application.filters.CategoryFilter;

import java.util.Optional;

public interface CategoryRepositoryPort {
  Optional<Category> findOne(CategoryFilter filter);
}
