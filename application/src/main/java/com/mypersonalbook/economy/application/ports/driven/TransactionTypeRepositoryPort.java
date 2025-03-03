package com.mypersonalbook.economy.application.ports.driven;

import java.util.Optional;

public interface TransactionTypeRepositoryPort {
  Optional<String> findById(String id);
}
