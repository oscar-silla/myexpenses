package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.ports.in.SaveTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import com.mypersonalbook.economy.services.TransactionTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.mypersonalbook.economy.utils.AppConstants.EXPENSE_TYPE;
import static com.mypersonalbook.economy.utils.AppConstants.REVENUE_TYPE;

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
