package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.ports.out.TransactionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.mypersonalbook.economy.utils.AppConstants.EXPENSE_TYPE;

@Service
public class TransactionService {
  private final TransactionRepositoryPort transactionRepository;
  private final CategoryRepositoryPort categoryRepository;
  private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

  public TransactionService(
      TransactionRepositoryPort transactionRepository, CategoryRepositoryPort categoryRepository) {
    this.transactionRepository = transactionRepository;
    this.categoryRepository = categoryRepository;
  }

  public void save(Transaction transaction) {
    Category category = this.findCategoryOrThrow(transaction);
    transaction.setCategory(category);
    this.transactionRepository.save(transaction);
  }

  public void deleteById(Long id) {
    this.transactionRepository.deleteById(id);
  }

  public Transaction findById(Long id) {
    return this.transactionRepository
        .findById(id)
        .orElseThrow(
            () -> {
              logger.error("Transaction with id: {} not found.", id);
              return new NotFoundException();
            });
  }

  public Page<Transaction> find(TransactionFilter transactionFilter) {
    return this.transactionRepository.find(transactionFilter);
  }

  public void modify(Transaction transaction) {
    if (transaction.getCategory() != null) {
      transaction.setCategory(this.findCategoryOrThrow(transaction));
    }
    Transaction transactionToUpdate = this.findById(transaction.getId());
    this.transactionRepository.modify(transaction, transactionToUpdate);
  }

  private Category findCategoryOrThrow(Transaction transaction) {
    return this.categoryRepository
        .findOne(
            new CategoryFilter(
                transaction.getCategory().getNames().get(0).getName(), transaction.getType()))
        .orElseThrow(
            () -> {
              logger.error(
                  "Category with name: {}, type: {} not found.",
                  transaction.getCategory().getNames(),
                  EXPENSE_TYPE);
              return new NotFoundException();
            });
  }
}
