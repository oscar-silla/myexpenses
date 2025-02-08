package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.models.CategoryMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryMapperTest {
  CategoryRepositoryMapper categoryRepositoryMapper;

  @BeforeEach
  void setUp() {
    CategoryNameRepositoryMapper categoryNameRepositoryMapper =
        new CategoryNameRepositoryMapperImpl();
    this.categoryRepositoryMapper = new CategoryRepositoryMapperImpl(categoryNameRepositoryMapper);
  }

  @Test
  @DisplayName("Should map to categoryMO")
  void shouldMapToCategoryMO() {
    final CategoryMO RESULT = this.categoryRepositoryMapper.toCategoryMO(EXPENSE_CATEGORY);
    assertEquals(CATEGORY_ID, RESULT.getId());
    assertEquals(CATEGORY_NAME_UPPER_CASE, RESULT.getNames().get(0).getName());
    assertEquals(CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE, RESULT.getType().getId());
  }
}
