package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.filters.CategoryFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_NAME_UPPER_CASE;
import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE;

public class CategoryFilterMock {
  public static final CategoryFilter CATEGORY_FILTER = new CategoryFilter(CATEGORY_NAME_UPPER_CASE,
      CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE);
}
