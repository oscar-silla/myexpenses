package com.mypersonalbook.economy.models.response.transaction;

public record TransactionSummaryResponse(
    Float totalRevenue, Float totalExpense, Float totalMoney) {}
