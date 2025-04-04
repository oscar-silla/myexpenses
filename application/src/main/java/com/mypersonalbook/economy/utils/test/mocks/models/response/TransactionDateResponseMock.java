package com.mypersonalbook.economy.utils.test.mocks.models.response;

import com.mypersonalbook.economy.models.response.transaction.TransactionDateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.TRANSACTION_DATE_1;
import static com.mypersonalbook.economy.utils.test.mocks.AmountSummaryMock.AMOUNT_SUMMARY;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionMock.*;

public class TransactionDateResponseMock {
  public static final TransactionDateResponse TRANSACTION_DATE_RESPONSE_1 =
      new TransactionDateResponse(
          TRANSACTION_DATE_1,
          List.of(EXPENSE_TRANSACTION_1),
          List.of(REVENUE_TRANSACTION_1),
          AMOUNT_SUMMARY);
  public static final TransactionDateResponse TRANSACTION_DATE_RESPONSE_2 =
      new TransactionDateResponse(
          TRANSACTION_DATE_1,
          List.of(EXPENSE_TRANSACTION_2),
          List.of(REVENUE_TRANSACTION_2),
          AMOUNT_SUMMARY);
  public static final Page<TransactionDateResponse> TRANSACTION_DATE_RESPONSE_PAGE =
      new PageImpl<>(List.of(TRANSACTION_DATE_RESPONSE_1, TRANSACTION_DATE_RESPONSE_2));
  public static final Page<TransactionDateResponse> TRANSACTION_DATE_RESPONSE_EMPTY_PAGE =
      new PageImpl<>(Collections.emptyList());
}
