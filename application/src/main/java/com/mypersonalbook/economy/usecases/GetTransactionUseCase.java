package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.ports.in.GetTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionUseCase implements GetTransactionUseCasePort {
  private final TransactionService transactionService;

  public GetTransactionUseCase(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public Transaction execute(Long id) {
    return this.transactionService.findById(id);
  }
}
