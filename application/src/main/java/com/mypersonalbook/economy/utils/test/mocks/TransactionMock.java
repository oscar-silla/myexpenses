package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.*;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.USER;

public class TransactionMock {
  public static final Transaction EXPENSE_TRANSACTION_1 =
      new Transaction(
          TRANSACTION_ID_1,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_2 =
      new Transaction(
          TRANSACTION_ID_2,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_3 =
      new Transaction(
          TRANSACTION_ID_3,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_2,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_4 =
      new Transaction(
          TRANSACTION_ID_4,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_2,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_5 =
      new Transaction(
          TRANSACTION_ID_1,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_TRANSACTION_TYPE_2,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_WITH_NULL_AMOUNT =
      new Transaction(
          TRANSACTION_ID_1,
          null,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_WITH_NULL_CATEGORY =
      new Transaction(
          TRANSACTION_ID_1,
          EXPENSE_TRANSACTION_AMOUNT,
          null,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_WITH_EMPTY_CATEGORY =
      new Transaction(
          TRANSACTION_ID_1,
          EXPENSE_TRANSACTION_AMOUNT,
          EMPTY_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction EXPENSE_TRANSACTION_WITH_NULL_DATE =
      new Transaction(
          TRANSACTION_ID_1,
          EXPENSE_TRANSACTION_AMOUNT,
          EXPENSE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          null,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction OTHER_EXPENSE_TRANSACTION =
      new Transaction(
          TRANSACTION_ID_2,
          REVENUE_TRANSACTION_AMOUNT,
          REVENUE_CATEGORY,
          TRANSACTION_DESCRIPTION_2,
          TRANSACTION_DATE_2,
          CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction REVENUE_TRANSACTION_1 =
      new Transaction(
          TRANSACTION_ID_1,
          REVENUE_TRANSACTION_AMOUNT,
          REVENUE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_REVENUE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Transaction REVENUE_TRANSACTION_2 =
      new Transaction(
          TRANSACTION_ID_2,
          REVENUE_TRANSACTION_AMOUNT,
          REVENUE_CATEGORY,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          CATEGORY_REVENUE_TRANSACTION_TYPE_UPPER_CASE,
          USER);
  public static final Page<Transaction> EXPENSE_TRANSACTIONS_PAGE =
      new PageImpl<>(List.of(EXPENSE_TRANSACTION_1));
  public static final Page<Transaction> REVENUE_TRANSACTIONS_PAGE =
      new PageImpl<>(List.of(EXPENSE_TRANSACTION_5));
  public static final Page<Transaction> EXPENSE_TRANSACTIONS_PAGE_2 =
      new PageImpl<>(List.of(EXPENSE_TRANSACTION_1, EXPENSE_TRANSACTION_2));
  public static final Page<Transaction> REVENUE_TRANSACTIONS_PAGE_2 =
      new PageImpl<>(List.of(REVENUE_TRANSACTION_1, REVENUE_TRANSACTION_2));
  public static final Page<Transaction> DISORDERED_EXPENSE_TRANSASCTIONS_PAGE =
      new PageImpl<>(
          List.of(
              EXPENSE_TRANSACTION_1,
              EXPENSE_TRANSACTION_2,
              EXPENSE_TRANSACTION_3,
              EXPENSE_TRANSACTION_4));
  public static final Page<Transaction> TRANSACTION_PAGE =
      new PageImpl<>(
          List.of(
              EXPENSE_TRANSACTION_1,
              EXPENSE_TRANSACTION_2,
              REVENUE_TRANSACTION_1,
              REVENUE_TRANSACTION_2));
}
