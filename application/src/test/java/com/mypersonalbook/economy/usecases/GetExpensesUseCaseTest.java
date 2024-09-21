package com.mypersonalbook.economy.usecases;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import com.mypersonalbook.economy.filters.ExpenseFilter;
import com.mypersonalbook.economy.filters.PaginationFilter;
import com.mypersonalbook.economy.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSES_PAGE;
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
}
