package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.filters.CategoryFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_NAME;
import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_TRANSACTION_TYPE;

public class CategoryFilterMock {
  public static final CategoryFilter CATEGORY_FILTER =
      new CategoryFilter(CATEGORY_NAME, CATEGORY_TRANSACTION_TYPE);
}
