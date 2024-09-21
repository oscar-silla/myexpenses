package com.mypersonalbook.economy.utils.mocks;

import openapi.economy.model.PaginationResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class PaginationResponseTypeMock {
  public static PaginationResponseType PAGINATION_RESPONSE_TYPE() {
    PaginationResponseType paginationResponseType = new PaginationResponseType();
    paginationResponseType.setPageNumber(PAGE_NUMBER);
    paginationResponseType.setPageNumber(PAGE_SIZE);
    paginationResponseType.setTotalResults(PAGE_TOTAL_RESULTS);
    return paginationResponseType;
  }
}
