package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.mappers.CategoryRepositoryMapper;
import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.repositories.CategoryJpaRepository;
import com.mypersonalbook.economy.specifications.CategorySpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMOMock.CATEGORY_MO;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryAdapterTest {
  CategoryRepositoryAdapter categoryRepositoryAdapter;
  @Mock private CategoryJpaRepository categoryJpaRepository;
  @Mock private CategorySpecification categorySpecification;
  @Mock private CategoryRepositoryMapper categoryRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.categoryRepositoryAdapter =
        new CategoryRepositoryAdapter(
            this.categoryJpaRepository, this.categorySpecification, this.categoryRepositoryMapper);
  }

  Specification<CategoryMO> mockSpecification =
      (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

  @Test
  @DisplayName("Should return category when find one")
  void shouldReturnCategory_WhenFindOne() {
    when(this.categorySpecification.getSpecification(any(CategoryFilter.class)))
        .thenReturn(mockSpecification);
    when(this.categoryJpaRepository.findOne(any(Specification.class)))
        .thenReturn(Optional.of(CATEGORY_MO));
    when(this.categoryRepositoryMapper.toCategory(any(CategoryMO.class)))
        .thenReturn(EXPENSE_CATEGORY);
    final Optional<Category> RESULT =
        this.categoryRepositoryAdapter.findOne(
            new CategoryFilter(
                CATEGORY_NAME_UPPER_CASE, CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE, USER_ID));
    assertTrue(RESULT.isPresent());
    assertEquals(CATEGORY_ID, RESULT.get().getId());
  }
}
