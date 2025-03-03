package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.ports.driving.transaction.GetTransactionUseCasePort;
import com.mypersonalbook.economy.application.services.TransactionService;
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
