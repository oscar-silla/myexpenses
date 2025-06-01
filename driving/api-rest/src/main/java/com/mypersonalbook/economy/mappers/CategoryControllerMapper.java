package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import openapi.economy.model.CategoriesResponseType;
import openapi.economy.model.CategoryResponseType;
import openapi.economy.model.PaginationResponseType;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CategoryControllerMapper {
  default CategoriesResponseType toCategoriesResponseType(Page<Category> categoryPage) {
    CategoriesResponseType categoriesResponseType = new CategoriesResponseType();
    categoriesResponseType.setResults(
        categoryPage.getContent().stream().map(this::toCategoryResponseType).toList());
    categoriesResponseType.setPagination(this.toPaginationResponseType(categoryPage));
    return categoriesResponseType;
  }

  default CategoryResponseType toCategoryResponseType(Category category) {
    CategoryResponseType categoryResponseType = new CategoryResponseType();
    categoryResponseType.setName(category.getName());
    categoryResponseType.setColor(category.getColor());
    return categoryResponseType;
  }

  default PaginationResponseType toPaginationResponseType(Page<Category> categoryPage) {
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageNumber(categoryPage.getNumber() + 1);
    paginationResponseType.setPageSize(categoryPage.getSize());
    paginationResponseType.setTotalResults((int) categoryPage.getTotalElements());
    return paginationResponseType;
  }
}
