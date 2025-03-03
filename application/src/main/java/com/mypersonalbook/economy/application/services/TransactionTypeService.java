package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.ports.driven.TransactionTypeRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService {
  private final TransactionTypeRepositoryPort transactionTypeRepository;

  public TransactionTypeService(TransactionTypeRepositoryPort transactionTypeRepository) {
    this.transactionTypeRepository = transactionTypeRepository;
  }

  public void findOrThrowNotFoundException(String type) {
    this.transactionTypeRepository.findById(type).orElseThrow(NotFoundException::new);
  }
}
