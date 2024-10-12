package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.ports.in.SaveTransactionUseCasePort;
import com.mypersonalbook.economy.services.TransactionService;
import com.mypersonalbook.economy.services.TransactionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveTransactionUseCaseTest {
  SaveTransactionUseCasePort saveTransactionUseCase;
  @Mock private TransactionService transactionService;
  @Mock private TransactionTypeService transactionTypeService;

  @BeforeEach
  void setUp() {
    this.saveTransactionUseCase =
        new SaveTransactionUseCase(this.transactionService, this.transactionTypeService);
  }

  @Test
  @DisplayName("Should throw bad request exception when amount is null")
  void shouldThrowBadRequestException_WhenAmountIsNull() {
    final Executable EXECUTABLE =
        () -> this.saveTransactionUseCase.execute(TRANSACTION_WITH_NULL_AMOUNT);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when category is null")
  void shouldThrowBadRequestException_WhenCategoryIsNull() {
    final Executable EXECUTABLE =
        () -> this.saveTransactionUseCase.execute(TRANSACTION_WITH_NULL_CATEGORY);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when category is empty")
  void shouldThrowBadRequestException_WhenCategoryIsEmpty() {
    final Executable EXECUTABLE =
        () -> this.saveTransactionUseCase.execute(TRANSACTION_WITH_EMPTY_CATEGORY);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when description is empty")
  void shouldThrowBadRequestException_WhenDescriptionIsEmpty() {
    final Executable EXECUTABLE =
        () -> this.saveTransactionUseCase.execute(TRANSACTION_WITH_EMPTY_DESCRIPTION);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw bad request exception when date is null")
  void shouldThrowBadRequestException_WhenDateIsNull() {
    final Executable EXECUTABLE =
        () -> this.saveTransactionUseCase.execute(TRANSACTION_WITH_NULL_DATE);
    assertThrows(BadRequestException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should save Transaction when execute")
  void shouldSaveTransaction_WhenExecute() {
    doNothing().when(this.transactionService).save(any(Transaction.class));
    this.saveTransactionUseCase.execute(TRANSACTION_1);
    verify(this.transactionService).save(any(Transaction.class));
  }
}
