package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.projections.CategoryProjection;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryRepositoryMapper {
  @Mapping(target = "user.id", source = "userId")
  CategoryMO toCategoryMO(Category category, Long userId);

  Category toCategory(CategoryMO categoryMO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapFromDtoToCategory(Category dto, @MappingTarget Category category);

  default Category toCategory(CategoryProjection categoryProjection) {
    return new Category(null, categoryProjection.getName(), categoryProjection.getColor());
  }
}
