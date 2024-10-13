package com.mypersonalbook.economy.utils.test;

import java.time.LocalDate;

public class TestConstants {
  public static final Long TRANSACTION_ID_1 = 1L;
  public static final Long TRANSACTION_ID_2 = 2L;
  public static final Long TRANSACTION_ID_3 = 3L;
  public static final Long TRANSACTION_ID_4 = 4L;
  public static final Float TRANSACTION_AMOUNT = 2.5F;
  public static final Float TRANSACTION_AMOUNT_2 = 3.5F;
  public static final String TRANSACTION_DESCRIPTION = "WATER";
  public static final String TRANSACTION_DESCRIPTION_2 = "CURRENCY";
  public static final LocalDate TRANSACTION_DATE_1 = LocalDate.now();
  public static final LocalDate TRANSACTION_DATE_2 = LocalDate.now().plusDays(1);
  public static final Long CATEGORY_ID = 2L;
  public static final Long CATEGORY_ID_2 = 3L;
  public static final String CATEGORY_NAME = "FOOD";
  public static final String CATEGORY_NAME_2 = "FINANCES";
  public static final String CATEGORY_TRANSACTION_TYPE = "EXPENSE";
  public static final String CATEGORY_TRANSACTION_TYPE_2 = "REVENUE";
  public static final Integer PAGE_NUMBER = 1;
  public static final Integer PAGE_SIZE = 10;
  public static final Integer PAGE_TOTAL_RESULTS = 100;
  public static final LocalDate START_DATE = LocalDate.now();
  public static final LocalDate END_DATE = LocalDate.now().plusDays(1);
}
