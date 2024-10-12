package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.DeleteTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
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
