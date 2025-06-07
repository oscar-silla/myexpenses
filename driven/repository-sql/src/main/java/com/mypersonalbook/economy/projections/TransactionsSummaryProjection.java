package com.mypersonalbook.economy.projections;

public interface TransactionsSummaryProjection {
  Float getTotalRevenue();

  Float getTotalExpense();

  Float getTotalMoney();
}
