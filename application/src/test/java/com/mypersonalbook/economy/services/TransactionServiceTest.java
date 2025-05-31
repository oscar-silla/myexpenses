package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.application.services.TransactionService;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.filters.CategoryFilter;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.application.ports.driven.CategoryRepositoryPort;
import com.mypersonalbook.economy.application.ports.driven.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTIONS_PAGE;
import static com.mypersonalbook.economy.utils.test.mocks.filters.TransactionFilterMock.TRANSACTION_FILTER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
  TransactionService transactionService;
  @Mock private TransactionRepositoryPort transactionRepository;

  @BeforeEach
  void setUp() {
    this.transactionService =
        new TransactionService(this.transactionRepository);
  }

  @Test
  @DisplayName("Should save transaction")
  void shouldSaveTransaction() {
    doNothing().when(this.transactionRepository).save(any(Transaction.class));
    this.transactionService.save(EXPENSE_TRANSACTION_1);
    verify(this.transactionRepository).save(any(Transaction.class));
  }

  @Test
  @DisplayName("Should delete transaction by id")
  void shouldDeleteTransactionById() {
    doNothing().when(this.transactionRepository).deleteById(anyLong());
    this.transactionService.deleteById(TRANSACTION_ID_1);
    verify(this.transactionRepository).deleteById(anyLong());
  }

  @Test
  @DisplayName("Should throw not found exception when find transaction by id")
  void shouldThrowNotFoundException_WhenFindTransactionById() {
    when(this.transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
    Executable executable = () -> this.transactionService.findById(TRANSACTION_ID_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should return transaction when find by id")
  void shouldReturnTransaction_WhenFindById() {
    when(this.transactionRepository.findById(anyLong()))
        .thenReturn(Optional.of(EXPENSE_TRANSACTION_1));
    Transaction transaction = this.transactionService.findById(TRANSACTION_ID_1);
    assertNotNull(transaction);
  }

  @Test
  @DisplayName("Should return transactions page when find")
  void shouldReturnTransactionsPage_WhenFind() {
    when(this.transactionRepository.find(any(TransactionFilter.class)))
        .thenReturn(EXPENSE_TRANSACTIONS_PAGE);
    this.transactionService.find(TRANSACTION_FILTER);
    verify(this.transactionRepository).find(any(TransactionFilter.class));
  }

  @Test
  @DisplayName("Should modify transaction")
  void shouldModifyTransaction() {
    doNothing()
        .when(this.transactionRepository)
        .modify(any(Transaction.class), any(Transaction.class));
    this.transactionService.modify(EXPENSE_TRANSACTION_1, EXPENSE_TRANSACTION_1);
    verify(this.transactionRepository).modify(any(Transaction.class), any(Transaction.class));
  }
}
