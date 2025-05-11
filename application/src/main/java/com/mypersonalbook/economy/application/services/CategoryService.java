package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.domain.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepositoryPort categoryRepository;

  public CategoryService(CategoryRepositoryPort categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  private Optional<Category> findByNameAndTypeAndUserId(String name, String type, Long userId) {
    return this.categoryRepository.findOne(new CategoryFilter(name, type, userId));
  }

  public Category save(Category category, Long userId) {
    final Optional<Category> currentCategory =
        this.findByNameAndTypeAndUserId(category.getName(), category.getType(), userId);
    return currentCategory.orElseGet(() -> this.categoryRepository.save(category, userId));
  }
}
