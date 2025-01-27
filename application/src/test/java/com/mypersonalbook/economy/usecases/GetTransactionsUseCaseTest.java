package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.filters.TransactionFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.models.response.TransactionDateResponse;
import com.mypersonalbook.economy.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTransactionsUseCaseTest {
  GetTransactionsUseCase getTransactionsUseCase;
  @Mock
  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    this.getTransactionsUseCase = new GetTransactionsUseCase(this.transactionService);
  }

  @Test
  @DisplayName("Should throw bad request exception when find transactions because dates are wrong")
  void shouldThrowBadRequestException_WhenFindTransactions_BecauseDatesAreWrong() {
    Executable executable = () -> this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, END_DATE, START_DATE);
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should find transactions when execute use case")
  void shouldFindTransactions_WhenExecuteUseCase() {
    when(this.transactionService.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(EXPENSE_TRANSACTIONS_PAGE);
    this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE, END_DATE);
    verify(this.transactionService).find(any(TransactionFilter.class), any(PaginationFilter.class));
  }

  @Test
  @DisplayName("Should sort response by descending date and descending id")
  void shouldSortResponse_ByDescendingDate_AndDescendingId() {
    when(this.transactionService.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(DISORDERED_EXPENSE_TRANSASCTIONS_PAGE);
    final Page<TransactionDateResponse> RESULT = this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE,
        END_DATE);
    assertEquals(TRANSACTION_DATE_2, RESULT.getContent().get(0).date());
    assertEquals(TRANSACTION_ID_4, RESULT.getContent().get(0).expenses().get(0).getId());
  }

  @Test
  @DisplayName("Should add revenues when build transaction date response")
  void shouldAddRevenues_WhenBuildTransactionDateResponse() {
    when(this.transactionService.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(REVENUE_TRANSACTIONS_PAGE);
    final Page<TransactionDateResponse> RESULT = this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE,
        END_DATE);
    assertEquals(1, RESULT.getContent().get(0).revenues().size());
  }

  @Test
  @DisplayName("Should sum revenues date amount when build transaction date response")
  void shouldSumRevenuesDateAmount_WhenBuildTransactionDateResponse() {
    when(this.transactionService.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(REVENUE_TRANSACTIONS_PAGE_2);
    final Page<TransactionDateResponse> RESULT = this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE,
        END_DATE);
    assertEquals(2, RESULT.getContent().get(0).revenues().size());
    assertEquals(TRANSACTION_AMOUNT * 2, RESULT.getContent().get(0).amount().getRevenue());
  }

  @Test
  @DisplayName("Should sum expenses date amount when build transaction date response")
  void shouldSumExpensesDateAmount_WhenBuildTransactionDateResponse() {
    when(this.transactionService.find(any(TransactionFilter.class), any(PaginationFilter.class)))
        .thenReturn(EXPENSE_TRANSACTIONS_PAGE_2);
    final Page<TransactionDateResponse> RESULT = this.getTransactionsUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE,
        END_DATE);
    assertEquals(2, RESULT.getContent().get(0).expenses().size());
    assertEquals(TRANSACTION_AMOUNT * 2, RESULT.getContent().get(0).amount().getExpense());
  }
}
