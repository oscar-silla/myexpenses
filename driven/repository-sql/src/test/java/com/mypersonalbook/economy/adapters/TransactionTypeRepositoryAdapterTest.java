package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.repositories.TransactionTypeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionTypeRepositoryAdapterTest {
  TransactionTypeRepositoryAdapter transactionTypeRepositoryAdapter;

  @Mock
  private TransactionTypeJpaRepository transactionTypeJpaRepository;

  @BeforeEach
  void setUp() {
    this.transactionTypeRepositoryAdapter = new TransactionTypeRepositoryAdapter(this.transactionTypeJpaRepository);
  }

  @Test
  @DisplayName("Should execute jpa method when find by id")
  void shouldExecuteJpaMethod_WhenFindById() {
    when(this.transactionTypeJpaRepository.findIdById(anyString())).thenReturn(Optional.empty());
    this.transactionTypeRepositoryAdapter.findById(CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE);
    verify(this.transactionTypeJpaRepository).findIdById(anyString());
  }
}
