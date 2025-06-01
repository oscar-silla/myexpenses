package com.mypersonalbook.economy.application.usecases.category;

import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.filters.PaginationFilter;
import com.mypersonalbook.economy.application.ports.driving.category.GetCategoriesUseCasePort;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.CategoryService;
import com.mypersonalbook.economy.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetCategoriesUseCase implements GetCategoriesUseCasePort {
  private final CategoryService categoryService;
  private final AuthService authService;

  public GetCategoriesUseCase(CategoryService categoryService, AuthService authService) {
    this.categoryService = categoryService;
    this.authService = authService;
  }

  @Override
  public Page<Category> getCategories(Integer pageNumber, Integer pageSize) {
    return this.categoryService.findAll(
        this.buildCategoryFilter(), this.buildPaginationFilter(pageNumber, pageSize));
  }

  private CategoryFilter buildCategoryFilter() {
    return new CategoryFilter(null, null, this.authService.getUserId());
  }

  private PaginationFilter buildPaginationFilter(Integer pageNumber, Integer pageSize) {
    return new PaginationFilter(pageNumber, pageSize);
  }
}
