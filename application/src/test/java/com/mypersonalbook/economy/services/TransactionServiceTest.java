package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.filters.CategoryFilter;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.ports.out.CategoryRepositoryPort;
import com.mypersonalbook.economy.ports.out.TransactionRepositoryPort;
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
import static com.mypersonalbook.economy.utils.test.mocks.filters.TransactionFilterMock.TRANSACTION_FILTER;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.EXPENSE_TRANSACTIONS_PAGE;
import static com.mypersonalbook.economy.utils.test.mocks.filters.PaginationFilterMock.PAGINATION_FILTER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
  TransactionService transactionService;
  @Mock private TransactionRepositoryPort transactionRepository;
  @Mock private CategoryRepositoryPort categoryRepository;

  @BeforeEach
  void setUp() {
    this.transactionService =
        new TransactionService(this.transactionRepository, this.categoryRepository);
  }

  @Test
  @DisplayName("Should throw not found exception when save")
  void shouldThrowNotFoundException_WhenSave() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.empty());
    Executable executable = () -> this.transactionService.save(TRANSACTION_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should save transaction")
  void shouldSaveTransaction() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    doNothing().when(this.transactionRepository).save(any(Transaction.class));
    this.transactionService.save(TRANSACTION_1);
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
    when(this.transactionRepository.findById(anyLong())).thenReturn(Optional.of(TRANSACTION_1));
    Transaction transaction = this.transactionService.findById(TRANSACTION_ID_1);
    assertNotNull(transaction);
  }

  @Test
  @DisplayName("Should return transactions page when find")
  void shouldReturnTransactionsPage_WhenFind() {
    when(this.transactionRepository.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(EXPENSE_TRANSACTIONS_PAGE);
    this.transactionService.find(TRANSACTION_FILTER, PAGINATION_FILTER);
    verify(this.transactionRepository)
        .find(any(TransactionFilter.class), any(PaginationFilter.class));
  }

  @Test
  @DisplayName("Should throw not found exception when modify transaction and category not exists")
  void shouldThrowNotFoundException_WhenModifyTransaction_AndCategoryNotExists() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class))).thenReturn(Optional.empty());
    Executable executable = () -> this.transactionService.modify(TRANSACTION_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName(
      "Should throw not found exception when modify transaction and transaction to update not exists")
  void shouldThrowNotFoundException_WhenModifyTransaction_AndTransactionToUpdateNotExists() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    when(this.transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
    Executable executable = () -> this.transactionService.modify(TRANSACTION_1);
    assertThrows(NotFoundException.class, executable);
  }

  @Test
  @DisplayName("Should modify transaction")
  void shouldModifyTransaction() {
    when(this.categoryRepository.findOne(any(CategoryFilter.class)))
        .thenReturn(Optional.of(EXPENSE_CATEGORY));
    when(this.transactionRepository.findById(anyLong())).thenReturn(Optional.of(TRANSACTION_1));
    doNothing()
        .when(this.transactionRepository)
        .modify(any(Transaction.class), any(Transaction.class));
    this.transactionService.modify(TRANSACTION_1);
    verify(this.transactionRepository).modify(any(Transaction.class), any(Transaction.class));
  }
}
