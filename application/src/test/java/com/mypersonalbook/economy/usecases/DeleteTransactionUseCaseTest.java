package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.DeleteTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_ID_1;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteTransactionUseCaseTest {
  DeleteTransactionUseCasePort deleteTransactionUseCase;
  @Mock private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    this.deleteTransactionUseCase = new DeleteTransactionUseCase(this.transactionService);
  }

  @Test
  @DisplayName("Should delete transaction by id")
  void shouldDeleteTransactionById() {
    doNothing().when(this.transactionService).deleteById(anyLong());
    this.deleteTransactionUseCase.execute(TRANSACTION_ID_1);
    verify(this.transactionService).deleteById(anyLong());
  }
}
