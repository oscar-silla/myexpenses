package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.ports.in.GetTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTION_1;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTransactionUseCaseTest {
  GetTransactionUseCasePort getTransactionUseCase;
  @Mock
  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    this.getTransactionUseCase = new GetTransactionUseCase(this.transactionService);
  }

  @Test
  @DisplayName("Should return transaction when find by id")
  void shouldReturnTransactionWhenFindById() {
    when(this.transactionService.findById(anyLong())).thenReturn(EXPENSE_TRANSACTION_1);
    this.getTransactionUseCase.execute(TRANSACTION_ID_1);
    verify(this.transactionService).findById(anyLong());
  }
}
