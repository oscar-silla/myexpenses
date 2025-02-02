package com.mypersonalbook.economy.queryparams;

import java.time.LocalDate;

public record GetTransactionsQueryParams(
    Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate) {}
