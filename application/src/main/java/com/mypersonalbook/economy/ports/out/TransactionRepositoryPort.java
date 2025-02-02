package com.mypersonalbook.economy.ports.out;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TransactionRepositoryPort {
  void save(Transaction transaction);

  void deleteById(Long id);

  Optional<Transaction> findById(Long id);

  Page<Transaction> find(TransactionFilter transactionFilter);

  void modify(Transaction transaction, Transaction transactionToUpdate);
}
