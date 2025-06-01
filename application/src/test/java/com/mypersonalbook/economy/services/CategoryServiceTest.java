package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.application.services.CategoryService;
import com.mypersonalbook.economy.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.USER_ID;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.filters.CategoryFilterMock.CATEGORY_FILTER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
  CategoryService categoryService;

  @Mock private CategoryRepositoryPort categoryRepository;

  @BeforeEach
  void setUp() {
    categoryService = new CategoryService(categoryRepository);
  }

  @Test
  @DisplayName("Should return value when findOne")
  void shouldReturnValue_WhenFindOne() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    final Optional<Category> RESULT = this.categoryService.findOne(CATEGORY_FILTER);
    verify(this.categoryRepository).findOne(any(CategoryFilter.class));
    assertTrue(RESULT.isPresent());
  }

  @Test
  @DisplayName("Should return empty when findOne")
  void shouldReturnEmpty_WhenFindOne() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.empty());
    final Optional<Category> RESULT = this.categoryService.findOne(CATEGORY_FILTER);
    verify(this.categoryRepository).findOne(any(CategoryFilter.class));
    assertFalse(RESULT.isPresent());
  }

  @Test
  @DisplayName("Should return value when save")
  void shouldReturnValue_WhenSave() {
    when(this.categoryRepository.save(any(), any())).thenReturn(EXPENSE_CATEGORY);
    final Category RESULT = this.categoryService.save(EXPENSE_CATEGORY, USER_ID);
    verify(this.categoryRepository).save(any(), any());
    assertEquals(RESULT, EXPENSE_CATEGORY);
  }
}
