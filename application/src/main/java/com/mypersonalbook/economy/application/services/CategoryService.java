package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepositoryPort categoryRepository;

  public CategoryService(CategoryRepositoryPort categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Optional<Category> findOne(CategoryFilter filter) {
    return this.categoryRepository.findOne(filter);
  }

  public Category findOneOrCreate(Category category, Long userId) {
    return this.findOne(new CategoryFilter(category.getName(), userId))
        .orElseGet(() -> this.categoryRepository.save(category, userId));
  }

  public Page<Category> findAll(CategoryFilter filter, PaginationFilter paginationFilter) {
    return this.categoryRepository.findAll(filter, paginationFilter);
  }
}
