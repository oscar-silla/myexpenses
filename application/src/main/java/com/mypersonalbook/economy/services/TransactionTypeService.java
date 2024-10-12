package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.ports.out.TransactionTypeRepositoryPort;
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
