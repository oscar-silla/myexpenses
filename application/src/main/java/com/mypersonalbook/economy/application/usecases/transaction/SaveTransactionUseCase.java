package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.ports.driving.transaction.SaveTransactionUseCasePort;
import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.application.services.TransactionTypeService;
import org.springframework.stereotype.Service;

@Service
public class SaveTransactionUseCase implements SaveTransactionUseCasePort {
  private final TransactionService transactionService;
  private final TransactionTypeService transactionTypeService;

  public SaveTransactionUseCase(
      TransactionService transactionService, TransactionTypeService transactionTypeService) {
    this.transactionService = transactionService;
    this.transactionTypeService = transactionTypeService;
  }

  @Override
  public void execute(Transaction transaction) {
    this.validate(transaction);
    this.transactionService.save(transaction);
  }

  private void validate(Transaction transaction) {
    if (!transaction.hasAmount()
        || !transaction.hasCategory()
        || transaction.hasEmptyDescription()
        || !transaction.hasDate()
        || !transaction.hasType()) {
      throw new BadRequestException();
    }
    this.transactionTypeService.findOrThrowNotFoundException(transaction.getType());
  }
}
