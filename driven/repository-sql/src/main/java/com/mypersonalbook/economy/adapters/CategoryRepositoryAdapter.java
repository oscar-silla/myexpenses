package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.mappers.CategoryRepositoryMapper;
import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.repositories.CategoryJpaRepository;
import com.mypersonalbook.economy.specifications.CategorySpecification;
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
}
