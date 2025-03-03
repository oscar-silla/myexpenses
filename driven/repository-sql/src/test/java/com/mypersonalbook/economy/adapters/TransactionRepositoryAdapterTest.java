package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.application.filters.TransactionFilter;
import com.mypersonalbook.economy.mappers.TransactionRepositoryMapper;
import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.repositories.TransactionJpaRepository;
import com.mypersonalbook.economy.specifications.TransactionSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_ID_1;
import static com.mypersonalbook.economy.utils.test.mocks.filters.TransactionFilterMock.TRANSACTION_FILTER;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMOMock.EXPENSES_PAGE_MO;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMOMock.TRANSACTION_MO;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryAdapterTest {
  TransactionRepositoryAdapter transactionRepositoryAdapter;
  @Mock private TransactionJpaRepository transactionJpaRepository;
  @Mock private TransactionSpecification transactionSpecification;
  @Mock private TransactionRepositoryMapper transactionRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.transactionRepositoryAdapter =
        new TransactionRepositoryAdapter(
            this.transactionJpaRepository,
            this.transactionSpecification,
            this.transactionRepositoryMapper);
  }

  @Test
  @DisplayName("Should save with JPA when save")
  void shouldSave_WithJPA_WhenSave() {
    when(this.transactionRepositoryMapper.toTransactionMO(any(Transaction.class)))
        .thenReturn(TRANSACTION_MO);
    when(this.transactionJpaRepository.save(any(TransactionMO.class))).thenReturn(TRANSACTION_MO);
    this.transactionRepositoryAdapter.save(EXPENSE_TRANSACTION_1);
    verify(this.transactionJpaRepository).save(any(TransactionMO.class));
  }

  @Test
  @DisplayName("Should delete transaction by id")
  void shouldDeleteTransactionById() {
    doNothing().when(this.transactionJpaRepository).deleteById(anyLong());
    this.transactionRepositoryAdapter.deleteById(TRANSACTION_ID_1);
    verify(this.transactionJpaRepository).deleteById(anyLong());
  }

  @Test
  @DisplayName("Should return transaction when find by id")
  void shouldReturnTransaction_WhenFindById() {
    when(this.transactionJpaRepository.findById(anyLong())).thenReturn(Optional.of(TRANSACTION_MO));
    when(this.transactionRepositoryMapper.toTransaction(any(TransactionMO.class)))
        .thenReturn(EXPENSE_TRANSACTION_1);
    final Optional<Transaction> RESULT =
        this.transactionRepositoryAdapter.findById(TRANSACTION_ID_1);
    assertTrue(RESULT.isPresent());
  }

  @Test
  @DisplayName("Should return transactions page when find")
  void shouldReturnTransactionsPage_WhenFind() {
    Specification<TransactionMO> specifications =
        (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    when(this.transactionSpecification.getSpecification(any(TransactionFilter.class)))
        .thenReturn(specifications);
    when(this.transactionJpaRepository.findAll(any(Specification.class), any(PageRequest.class)))
        .thenReturn(EXPENSES_PAGE_MO);
    when(this.transactionRepositoryMapper.toTransaction(any(TransactionMO.class)))
        .thenReturn(EXPENSE_TRANSACTION_1);
    this.transactionRepositoryAdapter.find(TRANSACTION_FILTER);
    verify(this.transactionRepositoryMapper).toTransaction(any(TransactionMO.class));
  }

  @Test
  @DisplayName("Should save transaction when modify")
  void shouldSaveTransaction_WhenModify() {
    doNothing()
        .when(this.transactionRepositoryMapper)
        .mapFromDtoToTransaction(any(Transaction.class), any(Transaction.class));
    when(this.transactionRepositoryMapper.toTransactionMO(any(Transaction.class)))
        .thenReturn(TRANSACTION_MO);
    when(this.transactionJpaRepository.save(any(TransactionMO.class))).thenReturn(TRANSACTION_MO);
    this.transactionRepositoryAdapter.modify(EXPENSE_TRANSACTION_1, OTHER_EXPENSE_TRANSACTION);
    verify(this.transactionJpaRepository).save(any(TransactionMO.class));
  }
}
