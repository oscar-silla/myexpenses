package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.models.CategoryMO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = CategoryNameRepositoryMapper.class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryRepositoryMapper {
  @Mapping(target = "type.id", source = "type")
  CategoryMO toCategoryMO(Category category);

  @Mapping(target = "type", source = "type.id")
  Category toCategory(CategoryMO categoryMO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapFromDtoToCategory(Category dto, @MappingTarget Category category);
}
