package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.domain.Transaction;
import com.mypersonalbook.economy.models.TransactionMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.EXPENSE_CATEGORY;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMOMock.TRANSACTION_MO;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.TRANSACTION_1;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.OTHER_TRANSACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryMapperTest {
  TransactionRepositoryMapper transactionRepositoryMapper;

  @BeforeEach
  void setUp() {
    CategoryRepositoryMapper categoryRepositoryMapper = new CategoryRepositoryMapperImpl();
    this.transactionRepositoryMapper =
        new TransactionRepositoryMapperImpl(categoryRepositoryMapper);
  }

  @Test
  @DisplayName("Should map to transactionMO")
  void shouldMapToTransactionMO() {
    final TransactionMO RESULT = this.transactionRepositoryMapper.toTransactionMO(TRANSACTION_1);
    assertEquals(TRANSACTION_ID_1, RESULT.getId());
    assertEquals(TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(CATEGORY_ID, RESULT.getCategory().getId());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(CATEGORY_TRANSACTION_TYPE, RESULT.getCategory().getType().getId());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map to transaction")
  void shouldMapToTransaction() {
    final Transaction RESULT = this.transactionRepositoryMapper.toTransaction(TRANSACTION_MO);
    assertEquals(TRANSACTION_ID_1, RESULT.getId());
    assertEquals(TRANSACTION_AMOUNT, RESULT.getAmount());
    assertEquals(CATEGORY_ID, RESULT.getCategory().getId());
    assertEquals(CATEGORY_NAME, RESULT.getCategory().getName());
    assertEquals(CATEGORY_TRANSACTION_TYPE, RESULT.getCategory().getType());
    assertEquals(TRANSACTION_DESCRIPTION, RESULT.getDescription());
    assertEquals(TRANSACTION_DATE_1, RESULT.getDate());
  }

  @Test
  @DisplayName("Should map from dto to transaction with transaction null properties")
  void shouldMapFromDtoToTransaction_WithTransactionNullProperties() {
    Transaction transactionToUpdate = new Transaction(null, null, null, null, null, null);
    this.transactionRepositoryMapper.mapFromDtoToTransaction(TRANSACTION_1, transactionToUpdate);
    assertEquals(TRANSACTION_ID_1, transactionToUpdate.getId());
    assertEquals(TRANSACTION_AMOUNT, transactionToUpdate.getAmount());
    assertEquals(CATEGORY_ID, transactionToUpdate.getCategory().getId());
    assertEquals(CATEGORY_NAME, transactionToUpdate.getCategory().getName());
    assertEquals(CATEGORY_TRANSACTION_TYPE, transactionToUpdate.getCategory().getType());
    assertEquals(TRANSACTION_DESCRIPTION, transactionToUpdate.getDescription());
    assertEquals(TRANSACTION_DATE_1, transactionToUpdate.getDate());
  }

  @Test
  @DisplayName("Should map from dto to transaction with transaction")
  void shouldMapFromDtoToTransaction_WithTransaction() {
    Category category = new Category(1L, "CATEGORY", "EXPENSE");
    Transaction transactionToUpdate =
        new Transaction(
            1L,
            2.5F,
            category,
            "TRANSACTION_DESCRIPTION",
            LocalDate.now(),
            CATEGORY_TRANSACTION_TYPE);
    this.transactionRepositoryMapper.mapFromDtoToTransaction(
        OTHER_TRANSACTION, transactionToUpdate);
    assertEquals(TRANSACTION_ID_2, transactionToUpdate.getId());
    assertEquals(TRANSACTION_AMOUNT_2, transactionToUpdate.getAmount());
    assertEquals(CATEGORY_ID_2, transactionToUpdate.getCategory().getId());
    assertEquals(CATEGORY_NAME_2, transactionToUpdate.getCategory().getName());
    assertEquals(CATEGORY_TRANSACTION_TYPE_2, transactionToUpdate.getCategory().getType());
    assertEquals(TRANSACTION_DESCRIPTION_2, transactionToUpdate.getDescription());
    assertEquals(TRANSACTION_DATE_2, transactionToUpdate.getDate());
  }

  @Test
  @DisplayName("Should map from dto to transaction with dto null properties")
  void shouldMapFromDtoToTransaction_WithNullDtoProperties() {
    Transaction dto = new Transaction(null, null, null, null, null, null);
    Transaction transactionToUpdate =
        new Transaction(
            TRANSACTION_ID_1,
            TRANSACTION_AMOUNT,
            EXPENSE_CATEGORY,
            TRANSACTION_DESCRIPTION,
            TRANSACTION_DATE_1,
            CATEGORY_TRANSACTION_TYPE);
    this.transactionRepositoryMapper.mapFromDtoToTransaction(dto, transactionToUpdate);
    assertNotNull(transactionToUpdate.getId());
    assertNotNull(transactionToUpdate.getAmount());
    assertNotNull(transactionToUpdate.getCategory());
    assertNotNull(transactionToUpdate.getCategory().getId());
    assertNotNull(transactionToUpdate.getCategory().getName());
    assertNotNull(transactionToUpdate.getCategory().getType());
    assertNotNull(transactionToUpdate.getDescription());
    assertNotNull(transactionToUpdate.getDate());
  }
}
