package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.models.CategoryMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRepositoryMapper {
  CategoryMO toCategoryMO(Category category);

  Category toCategory(CategoryMO categoryMO);
}
