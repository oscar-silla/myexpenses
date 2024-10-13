package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.*;

public class TransactionMock {
  public static final Transaction TRANSACTION_1 =
      new Transaction(
          TRANSACTION_ID_1,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_2 =
      new Transaction(
          TRANSACTION_ID_2,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_3 =
      new Transaction(
          TRANSACTION_ID_3,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_2,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_4 =
      new Transaction(
          TRANSACTION_ID_4,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_2,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_5 =
          new Transaction(
                  TRANSACTION_ID_1,
                  TRANSACTION_AMOUNT,
                  EXPENSE_CATEGORY,
                  TRANSACTION_DESCRIPTION,
                  TRANSACTION_DATE_1,
                  CATEGORY_TRANSACTION_TYPE_2);
  public static final Transaction TRANSACTION_WITH_NULL_AMOUNT =
      new Transaction(
          TRANSACTION_ID_1,
          null,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_WITH_NULL_CATEGORY =
      new Transaction(
          TRANSACTION_ID_1,
          TRANSACTION_AMOUNT,
          null,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_WITH_EMPTY_CATEGORY =
      new Transaction(
          TRANSACTION_ID_1,
          TRANSACTION_AMOUNT,
          EMPTY_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_WITH_EMPTY_DESCRIPTION =
      new Transaction(
          TRANSACTION_ID_1,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          "  ",
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction TRANSACTION_WITH_NULL_DATE =
      new Transaction(
          TRANSACTION_ID_1,
          TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          null,
          CATEGORY_TRANSACTION_TYPE);
  public static final Transaction OTHER_TRANSACTION =
      new Transaction(
          TRANSACTION_ID_2,
          TRANSACTION_AMOUNT_2,
              REVENUE_CATEGORY,
          TRANSACTION_DESCRIPTION_2,
          TRANSACTION_DATE_2,
          CATEGORY_TRANSACTION_TYPE);
  public static final Page<Transaction> EXPENSE_TRANSACTIONS_PAGE =
      new PageImpl<>(List.of(TRANSACTION_1));
  public static final Page<Transaction> REVENUE_TRANSACTIONS_PAGE =
          new PageImpl<>(List.of(TRANSACTION_5));
  public static final Page<Transaction> DISORDERED_EXPENSE_TRANSASCTIONS_PAGE =
      new PageImpl<>(List.of(TRANSACTION_1, TRANSACTION_2, TRANSACTION_3, TRANSACTION_4));
}
