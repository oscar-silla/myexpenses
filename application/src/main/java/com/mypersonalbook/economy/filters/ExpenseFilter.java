package com.mypersonalbook.economy.filters;

import java.time.LocalDate;

public record ExpenseFilter(LocalDate startDate, LocalDate endDate) {}
