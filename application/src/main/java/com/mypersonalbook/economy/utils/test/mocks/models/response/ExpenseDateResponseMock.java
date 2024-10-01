package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.ExpenseDateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.EXPENSE_DATE_1;
import static com.mypersonalbook.economy.utils.test.mocks.ExpenseMock.EXPENSE_1;

public class ExpenseDateResponseMock {
  public static final ExpenseDateResponse EXPENSE_DATE_RESPONSE =
      new ExpenseDateResponse(EXPENSE_DATE_1, List.of(EXPENSE_1));
  public static final Page<ExpenseDateResponse> EXPENSE_DATE_RESPONSE_PAGE =
      new PageImpl<>(List.of(EXPENSE_DATE_RESPONSE));
}
