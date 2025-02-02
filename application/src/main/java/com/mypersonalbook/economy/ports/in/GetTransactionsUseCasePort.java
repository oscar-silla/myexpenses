package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.queryparams.GetTransactionsQueryParams;

public interface GetTransactionsUseCasePort {
  TransactionsResponse execute(GetTransactionsQueryParams queryParams);
}
