package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.LocaleName;
import com.mypersonalbook.economy.models.CategoryNameMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryNameRepositoryMapper {
    @Mapping(target = "name",source = "name")
    @Mapping(target = "locale", source = "id.locale")
    LocaleName toLocaleName(CategoryNameMO categoryNameMO);

    List<LocaleName> toLocaleNames(List<CategoryNameMO> categoryNameMOS);
}
