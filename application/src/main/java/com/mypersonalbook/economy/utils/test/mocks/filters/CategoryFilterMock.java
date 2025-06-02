package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.application.filters.CategoryFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class CategoryFilterMock {
  public static final CategoryFilter CATEGORY_FILTER =
      new CategoryFilter(CATEGORY_NAME_UPPER_CASE, USER_ID);
}
