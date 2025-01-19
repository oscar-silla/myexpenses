package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.exceptions.NotFoundException;
import com.mypersonalbook.economy.ports.out.TransactionTypeRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_TRANSACTION_TYPE_UPPER_CASE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionTypeServiceTest {
  TransactionTypeService transactionTypeService;

  @Mock private TransactionTypeRepositoryPort transactionTypeRepository;

  @BeforeEach
  void setUp() {
    this.transactionTypeService = new TransactionTypeService(this.transactionTypeRepository);
  }

  @Test
  @DisplayName("Should not throw exception when find or throw not found exception")
  void shouldNotThrowException_WhenFindOrThrowNotFoundException() {
    when(this.transactionTypeRepository.findById(anyString()))
        .thenReturn(Optional.of(CATEGORY_TRANSACTION_TYPE_UPPER_CASE));
    final Executable EXECUTABLE =
        () -> this.transactionTypeService.findOrThrowNotFoundException(CATEGORY_TRANSACTION_TYPE_UPPER_CASE);
    assertDoesNotThrow(EXECUTABLE);
  }

  @Test
  @DisplayName("Should throw exception when find or throw not found exception")
  void shouldThrowException_WhenFindOrThrowNotFoundException() {
    when(this.transactionTypeRepository.findById(anyString())).thenReturn(Optional.empty());
    final Executable EXECUTABLE =
        () -> this.transactionTypeService.findOrThrowNotFoundException(CATEGORY_TRANSACTION_TYPE_UPPER_CASE);
    assertThrows(NotFoundException.class, EXECUTABLE);
  }
}
