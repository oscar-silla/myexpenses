package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.pagination.PaginationResponse;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class PaginationResponseMock {
  public static final PaginationResponse PAGINATION_RESPONSE =
      new PaginationResponse(PAGE_NUMBER, PAGE_SIZE, PAGE_TOTAL_RESULTS);
}
