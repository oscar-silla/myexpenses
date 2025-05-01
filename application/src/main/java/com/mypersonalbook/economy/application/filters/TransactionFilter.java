package com.mypersonalbook.economy.application.filters;

import java.time.LocalDate;

public record TransactionFilter(
    Long userId, LocalDate startDate, LocalDate endDate, PaginationFilter paginationFilter) {}
