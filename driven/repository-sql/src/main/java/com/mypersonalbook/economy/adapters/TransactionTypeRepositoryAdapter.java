package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driven.TransactionTypeRepositoryPort;
import com.mypersonalbook.economy.projections.IdProjection;
import com.mypersonalbook.economy.repositories.TransactionTypeJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTypeRepositoryAdapter implements TransactionTypeRepositoryPort {
  private final TransactionTypeJpaRepository transactionTypeJpaRepository;

  public TransactionTypeRepositoryAdapter(
      TransactionTypeJpaRepository transactionTypeJpaRepository) {
    this.transactionTypeJpaRepository = transactionTypeJpaRepository;
  }

  @Override
  public Optional<String> findById(String id) {
    return this.transactionTypeJpaRepository.findIdById(id).map(IdProjection::getId);
  }
}
