package com.mypersonalbook.economy.ports.out;

import java.util.Optional;

public interface TransactionTypeRepositoryPort {
  Optional<String> findById(String id);
}
