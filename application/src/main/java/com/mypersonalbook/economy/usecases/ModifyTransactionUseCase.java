package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.ports.in.ModifyTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
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
