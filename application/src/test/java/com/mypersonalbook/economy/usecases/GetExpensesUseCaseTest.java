package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.models.response.ExpenseDateResponse;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetExpensesUseCaseTest {
  GetExpensesUseCase getExpensesUseCase;
  @Mock private ExpenseService expenseService;

  @BeforeEach
  void setUp() {
    this.getExpensesUseCase = new GetExpensesUseCase(this.expenseService);
  }

  @Test
  @DisplayName("Should throw bad request exception when find expenses because dates are wrong")
  void shouldThrowBadRequestException_WhenFindExpenses_BecauseDatesAreWrong() {
    Executable executable =
        () -> this.getExpensesUseCase.execute(PAGE_NUMBER, PAGE_SIZE, END_DATE, START_DATE);
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should find expenses when execute use case")
  void shouldFindExpenses_WhenExecuteUseCase() {
    when(this.expenseService.find(any(ExpenseFilter.class), any(PaginationFilter.class)))
        .thenReturn(EXPENSES_PAGE);
    this.getExpensesUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE, END_DATE);
    verify(this.expenseService).find(any(ExpenseFilter.class), any(PaginationFilter.class));
  }

  @Test
  @DisplayName("Should sort response by descending date and descending id")
  void shouldSortResponse_ByDescendingDate_AndDescendingId() {
    when(this.expenseService.find(any(ExpenseFilter.class), any(PaginationFilter.class)))
        .thenReturn(DISORDERED_EXPENSES_PAGE);
    final Page<ExpenseDateResponse> RESULT =
        this.getExpensesUseCase.execute(PAGE_NUMBER, PAGE_SIZE, START_DATE, END_DATE);
    assertEquals(EXPENSE_DATE_2, RESULT.getContent().get(0).getDate());
    assertEquals(EXPENSE_ID_4, RESULT.getContent().get(0).getExpenses().get(0).getId());
  }
}
