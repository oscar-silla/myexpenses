package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.TRANSACTION_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModifyTransactionUseCaseTest {
  ModifyTransactionUseCase modifyTransactionUseCase;
  @Mock private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    this.modifyTransactionUseCase = new ModifyTransactionUseCase(this.transactionService);
  }

  @Test
  @DisplayName("Should modify transaction when execute use case")
  void shouldModifyTransaction_WhenExecuteUseCase() {
    doNothing().when(this.transactionService).modify(any(Transaction.class));
    this.modifyTransactionUseCase.execute(TRANSACTION_1);
    verify(this.transactionService).modify(any(Transaction.class));
  }
}
