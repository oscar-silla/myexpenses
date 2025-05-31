package com.mypersonalbook.economy.usecases.transaction;

import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.CategoryService;
import com.mypersonalbook.economy.application.usecases.transaction.ModifyTransactionUseCase;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModifyTransactionUseCaseTest {
  ModifyTransactionUseCase modifyTransactionUseCase;
  @Mock private TransactionService transactionService;
  @Mock private CategoryService categoryService;
  @Mock private AuthService authService;

  @BeforeEach
  void setUp() {
    this.modifyTransactionUseCase =
        new ModifyTransactionUseCase(
            this.transactionService, this.categoryService, this.authService);
  }

  @Test
  @DisplayName("Should modify transaction when execute use case")
  void shouldModifyTransaction_WhenExecuteUseCase() {
    when(this.transactionService.findById(anyLong())).thenReturn(EXPENSE_TRANSACTION_1);
    doNothing()
        .when(this.transactionService)
        .modify(any(Transaction.class), any(Transaction.class));
    this.modifyTransactionUseCase.execute(EXPENSE_TRANSACTION_1);
    verify(this.transactionService).modify(any(Transaction.class), any(Transaction.class));
  }

  @Test
  @DisplayName("Should modify transaction with new category when execute use case")
  void shouldModifyTransaction_WithNewCategory_WhenExecuteUseCase() {
    when(this.transactionService.findById(anyLong()))
        .thenReturn(EXPENSE_TRANSACTION_WITH_DIFFERENT_CATEGORY);
    when(this.categoryService.findOneOrThrow(any()))
        .thenReturn(EXPENSE_TRANSACTION_2.getCategory());
    doNothing()
        .when(this.transactionService)
        .modify(any(Transaction.class), any(Transaction.class));
    this.modifyTransactionUseCase.execute(EXPENSE_TRANSACTION_1);
    verify(this.categoryService).findOneOrThrow(any());
    verify(this.transactionService).modify(any(Transaction.class), any(Transaction.class));
  }
}
