package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.ports.driving.transaction.ModifyTransactionUseCasePort;
import com.mypersonalbook.economy.application.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class ModifyTransactionUseCase implements ModifyTransactionUseCasePort {
  private final TransactionService transactionService;

  public ModifyTransactionUseCase(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public void execute(Transaction transaction) {
    this.transactionService.modify(transaction);
  }
}
