package com.mypersonalbook.economy.application.ports.driving.transaction;

import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;

public interface GetTransactionsUseCasePort {
  TransactionsResponse execute(GetTransactionsQueryParams queryParams);
}
