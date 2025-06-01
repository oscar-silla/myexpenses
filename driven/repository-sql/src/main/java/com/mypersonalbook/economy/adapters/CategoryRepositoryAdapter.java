package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.mappers.CategoryRepositoryMapper;
import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.repositories.CategoryJpaRepository;
import com.mypersonalbook.economy.specifications.CategorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {
  private final CategoryJpaRepository categoryJpaRepository;
  private final CategorySpecification categorySpecification;
  private final CategoryRepositoryMapper categoryRepositoryMapper;

  public CategoryRepositoryAdapter(
      CategoryJpaRepository categoryJpaRepository,
      CategorySpecification categorySpecification,
      CategoryRepositoryMapper categoryRepositoryMapper) {
    this.categoryJpaRepository = categoryJpaRepository;
    this.categorySpecification = categorySpecification;
    this.categoryRepositoryMapper = categoryRepositoryMapper;
  }

  @Override
  public Optional<Category> findOne(CategoryFilter filter) {
    Optional<CategoryMO> categoryMO =
        this.categoryJpaRepository.findOne(this.categorySpecification.getSpecification(filter));
    return categoryMO.map(this.categoryRepositoryMapper::toCategory);
  }

  @Override
  public Category save(Category category, Long userId) {
    return this.categoryRepositoryMapper.toCategory(
        this.categoryJpaRepository.save(
            this.categoryRepositoryMapper.toCategoryMO(category, userId)));
  }

  @Override
  public Page<Category> findAll(CategoryFilter filter, PaginationFilter paginationFilter) {
    PageRequest pageRequest =
        PageRequest.of(paginationFilter.pageNumber() - 1, paginationFilter.pageSize());
    return this.categoryJpaRepository
        .findAllByUserId(filter.userId(), pageRequest)
        .map(this.categoryRepositoryMapper::toCategory);
  }
}
