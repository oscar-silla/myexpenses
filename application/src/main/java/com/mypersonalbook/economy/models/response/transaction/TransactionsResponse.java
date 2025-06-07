package com.mypersonalbook.economy.models.response.transaction;

import com.mypersonalbook.economy.domain.Transaction;
import org.springframework.data.domain.Page;

public record TransactionsResponse(
    Page<Transaction> results,
    TransactionsSummary summary) {}
