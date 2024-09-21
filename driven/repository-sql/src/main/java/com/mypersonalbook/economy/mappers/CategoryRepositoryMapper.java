package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.models.CategoryMO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryRepositoryMapper {
  CategoryMO toCategoryMO(Category category);

  Category toCategory(CategoryMO categoryMO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapFromDtoToCategory(Category dto, @MappingTarget Category category);
}
