package com.mypersonalbook.economy.services.adhoc;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;
import static com.mypersonalbook.economy.utils.test.mocks.queryparams.GetTransactionQueryParamsMock.GET_TRANSACTIONS_QUERY_PARAMS_WITH_WRONG_DATES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionDateServiceTest {
  TransactionDateService transactionDateService;

  @BeforeEach
  void setUp() {
    this.transactionDateService = new TransactionDateService();
  }

  @Test
  @DisplayName("Should throw bad request exception when find transactions because dates are wrong")
  void shouldThrowBadRequestException_WhenFindTransactions_BecauseDatesAreWrong() {
    Executable executable =
        () ->
            this.transactionDateService.validateQueryParamsAndGetFilters(
                GET_TRANSACTIONS_QUERY_PARAMS_WITH_WRONG_DATES);
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should sort response by descending date and descending id")
  void shouldSortResponse_ByDescendingDate_AndDescendingId() {
    final Page<TransactionDateResponse> RESULT =
        this.transactionDateService.collectTransactionDateResponsePage(
            DISORDERED_EXPENSE_TRANSASCTIONS_PAGE);
    assertEquals(TRANSACTION_DATE_2, RESULT.getContent().get(0).date());
    assertEquals(TRANSACTION_ID_4, RESULT.getContent().get(0).expenses().get(0).getId());
  }

  @Test
  @DisplayName("Should add revenues when build transaction date response")
  void shouldAddRevenues_WhenBuildTransactionDateResponse() {
    final Page<TransactionDateResponse> RESULT =
        this.transactionDateService.collectTransactionDateResponsePage(REVENUE_TRANSACTIONS_PAGE);
    assertEquals(1, RESULT.getContent().get(0).revenues().size());
  }

  @Test
  @DisplayName("Should sum revenues date amount when build transaction date response")
  void shouldSumRevenuesDateAmount_WhenBuildTransactionDateResponse() {
    final Page<TransactionDateResponse> RESULT =
        this.transactionDateService.collectTransactionDateResponsePage(REVENUE_TRANSACTIONS_PAGE_2);
    assertEquals(2, RESULT.getContent().get(0).revenues().size());
    assertEquals(TRANSACTION_AMOUNT * 2, RESULT.getContent().get(0).amount().getRevenue());
  }

  @Test
  @DisplayName("Should sum expenses date amount when build transaction date response")
  void shouldSumExpensesDateAmount_WhenBuildTransactionDateResponse() {
    final Page<TransactionDateResponse> RESULT =
        this.transactionDateService.collectTransactionDateResponsePage(EXPENSE_TRANSACTIONS_PAGE_2);
    assertEquals(2, RESULT.getContent().get(0).expenses().size());
    assertEquals(TRANSACTION_AMOUNT * 2, RESULT.getContent().get(0).amount().getExpense());
  }
}
