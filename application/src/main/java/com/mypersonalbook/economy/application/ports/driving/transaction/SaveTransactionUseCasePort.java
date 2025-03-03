package com.mypersonalbook.economy.application.ports.driving.transaction;

import com.mypersonalbook.economy.domain.Transaction;

public interface SaveTransactionUseCasePort {
  void execute(Transaction transaction);
}
