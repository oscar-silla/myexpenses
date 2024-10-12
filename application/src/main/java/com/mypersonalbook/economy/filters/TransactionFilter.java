package com.mypersonalbook.economy.filters;

import java.time.LocalDate;

public record TransactionFilter(LocalDate startDate, LocalDate endDate) {}
