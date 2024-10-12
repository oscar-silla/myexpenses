package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Transaction;

public interface SaveTransactionUseCasePort {
  void execute(Transaction transaction);
}
