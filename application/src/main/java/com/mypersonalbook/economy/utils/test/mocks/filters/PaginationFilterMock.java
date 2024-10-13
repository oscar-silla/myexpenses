package com.mypersonalbook.economy.utils.test.mocks.filters;

import com.mypersonalbook.economy.filters.PaginationFilter;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class PaginationFilterMock {
  public static final PaginationFilter PAGINATION_FILTER =
      new PaginationFilter(PAGE_NUMBER, PAGE_SIZE);
}
