package com.mypersonalbook.economy.application.ports.driven;

import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoryRepositoryPort {
  Optional<Category> findOne(CategoryFilter filter);

  Category save(Category category, Long userId);

  Page<Category> findAll(CategoryFilter filter, PaginationFilter paginationFilter);
}
