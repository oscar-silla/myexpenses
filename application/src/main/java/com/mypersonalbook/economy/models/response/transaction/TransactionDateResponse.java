package com.mypersonalbook.economy.models.response.transaction;

import com.mypersonalbook.economy.domain.AmountSummary;
import com.mypersonalbook.economy.domain.Transaction;

import java.time.LocalDate;
import java.util.List;

public record TransactionDateResponse(
        LocalDate date, List<Transaction> expenses, List<Transaction> revenues, AmountSummary amount) {
}
