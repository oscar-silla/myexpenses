package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.CategoryService;
import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.ports.driving.transaction.ModifyTransactionUseCasePort;
import com.mypersonalbook.economy.application.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class ModifyTransactionUseCase implements ModifyTransactionUseCasePort {
  private final TransactionService transactionService;
  private final CategoryService categoryService;
  private final AuthService authService;

  public ModifyTransactionUseCase(
      TransactionService transactionService,
      CategoryService categoryService,
      AuthService authService) {
    this.transactionService = transactionService;
    this.categoryService = categoryService;
    this.authService = authService;
  }

  @Override
  public void execute(Transaction transaction) {
    final Transaction transactionToUpdate = this.transactionService.findById(transaction.getId());
    if (this.isCategoryModified(transaction, transactionToUpdate)) {
      Category category =
          this.categoryService.findOneOrThrow(this.buildCategoryFilter(transaction));
      transaction.setCategory(category);
    }
    this.transactionService.modify(transaction, transactionToUpdate);
  }

  private boolean isCategoryModified(Transaction transaction, Transaction transactionToUpdate) {
    return !transaction.getCategory().getName().equals(transactionToUpdate.getCategory().getName());
  }

  private CategoryFilter buildCategoryFilter(Transaction transaction) {
    return new CategoryFilter(
        transaction.getCategory().getName(), transaction.getType(), this.authService.getUserId());
  }
}
