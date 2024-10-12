package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Transaction;

public interface GetTransactionUseCasePort {
  Transaction execute(Long id);
}
