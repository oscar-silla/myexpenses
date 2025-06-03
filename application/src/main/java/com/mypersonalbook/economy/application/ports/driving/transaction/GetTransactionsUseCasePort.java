package com.mypersonalbook.economy.application.ports.driving.transaction;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.response.transaction.TransactionsResponse;
import com.mypersonalbook.economy.application.queryparams.GetTransactionsQueryParams;
import org.springframework.data.domain.Page;

public interface GetTransactionsUseCasePort {
  Page<Transaction> execute(GetTransactionsQueryParams queryParams);
}
