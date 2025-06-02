package com.mypersonalbook.economy.application.usecases.transaction;

import com.mypersonalbook.economy.application.exceptions.UnauthorizedException;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.CategoryService;
import com.mypersonalbook.economy.domain.Category;
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
  private final CategoryService categoryService;
  private final AuthService authService;

  public SaveTransactionUseCase(
      TransactionService transactionService,
      TransactionTypeService transactionTypeService,
      CategoryService categoryService,
      AuthService authService) {
    this.transactionService = transactionService;
    this.transactionTypeService = transactionTypeService;
    this.categoryService = categoryService;
    this.authService = authService;
  }

  @Override
  public void execute(Transaction transaction) {
    this.validate(transaction);
    transaction.getUser().setId(this.authService.getUserId());
    Category category =
        this.categoryService.findOneOrCreate(
            this.populateCategory(transaction), buildCategoryFilter(transaction));
    this.transactionService.save(this.populateTransaction(transaction, category));
  }

  private void validate(Transaction transaction) {
    if (!transaction.hasAmount()
        || !transaction.hasCategory()
        || !transaction.hasDate()
        || !transaction.hasType()) {
      throw new BadRequestException();
    }
    if (this.authService.getUserId() == null) {
      throw new UnauthorizedException();
    }
    this.transactionTypeService.findOrThrowNotFoundException(transaction.getType());
  }

  private Category populateCategory(Transaction transaction) {
    return new Category(
        null, transaction.getCategory().getName(), transaction.getCategory().getColor());
  }

  private Transaction populateTransaction(Transaction transaction, Category category) {
    transaction.setCategory(category);
    return transaction;
  }

  private CategoryFilter buildCategoryFilter(Transaction transaction) {
    return new CategoryFilter(transaction.getCategory().getName(), this.authService.getUserId());
  }
}
