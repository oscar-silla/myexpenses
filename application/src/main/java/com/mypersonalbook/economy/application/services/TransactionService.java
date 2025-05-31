package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.application.ports.driven.TransactionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {
  private final TransactionRepositoryPort transactionRepository;
  private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

  public TransactionService(TransactionRepositoryPort transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public void save(Transaction transaction) {
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

  public void modify(Transaction transaction, Transaction transactionToUpdate) {
    this.transactionRepository.modify(transaction, transactionToUpdate);
  }
}
