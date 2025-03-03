package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.ports.driving.transaction.DeleteTransactionUseCasePort;
import com.mypersonalbook.economy.application.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransactionUseCase implements DeleteTransactionUseCasePort {
  private final TransactionService transactionService;

  public DeleteTransactionUseCase(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public void execute(Long id) {
    this.transactionService.deleteById(id);
  }
}
