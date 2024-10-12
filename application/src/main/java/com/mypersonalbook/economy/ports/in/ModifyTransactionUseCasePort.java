package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Transaction;

public interface ModifyTransactionUseCasePort {
  void execute(Transaction transaction);
}
