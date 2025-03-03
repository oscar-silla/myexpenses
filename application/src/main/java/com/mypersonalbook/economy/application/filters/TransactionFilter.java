package com.mypersonalbook.economy.application.filters;

import java.time.LocalDate;

public record TransactionFilter(
    LocalDate startDate, LocalDate endDate, PaginationFilter paginationFilter) {}
