package com.mypersonalbook.economy.models.response.transaction;

import com.mypersonalbook.economy.models.response.pagination.PaginationResponse;

import java.util.List;

public record TransactionsResponse(
    List<TransactionDateResponse> results,
    TransactionSummaryResponse summary,
    PaginationResponse pagination) {}
