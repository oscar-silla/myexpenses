package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.mappers.TransactionRepositoryMapper;
import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.application.ports.driven.TransactionRepositoryPort;
import com.mypersonalbook.economy.repositories.TransactionJpaRepository;
import com.mypersonalbook.economy.specifications.TransactionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
  private final TransactionJpaRepository transactionJpaRepository;
  private final TransactionSpecification transactionSpecification;
  private final TransactionRepositoryMapper transactionRepositoryMapper;

  public TransactionRepositoryAdapter(
      TransactionJpaRepository transactionJpaRepository,
      TransactionSpecification transactionSpecification,
      TransactionRepositoryMapper transactionRepositoryMapper) {
    this.transactionJpaRepository = transactionJpaRepository;
    this.transactionSpecification = transactionSpecification;
    this.transactionRepositoryMapper = transactionRepositoryMapper;
  }

  @Override
  public void save(Transaction transaction) {
    this.transactionJpaRepository.save(
        this.transactionRepositoryMapper.toTransactionMO(transaction));
  }

  @Override
  public void deleteById(Long id) {
    this.transactionJpaRepository.deleteById(id);
  }

  @Override
  public Optional<Transaction> findById(Long id) {
    return this.transactionJpaRepository
        .findById(id)
        .map(this.transactionRepositoryMapper::toTransaction);
  }

  @Override
  public Page<Transaction> find(TransactionFilter transactionFilter) {
    Specification<TransactionMO> specifications =
        this.transactionSpecification.getSpecification(transactionFilter);
    Pageable pageable =
        PageRequest.of(
            transactionFilter.paginationFilter().pageNumber() - 1,
            2000);
    Page<TransactionMO> expensesMOPage =
        this.transactionJpaRepository.findAll(specifications, pageable);
    return expensesMOPage.map(this.transactionRepositoryMapper::toTransaction);
  }

  @Override
  public void modify(Transaction transaction, Transaction transactionToUpdate) {
    this.transactionRepositoryMapper.mapFromDtoToTransaction(transaction, transactionToUpdate);
    TransactionMO transactionMO =
        this.transactionRepositoryMapper.toTransactionMO(transactionToUpdate);
    this.transactionJpaRepository.save(transactionMO);
  }
}
