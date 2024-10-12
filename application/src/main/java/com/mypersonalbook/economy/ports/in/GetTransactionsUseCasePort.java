package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.models.response.TransactionDateResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface GetTransactionsUseCasePort {
  Page<TransactionDateResponse> execute(
      Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate);
}
