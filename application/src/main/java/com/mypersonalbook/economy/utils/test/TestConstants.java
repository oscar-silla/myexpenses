package com.mypersonalbook.economy.utils.test;

import java.time.LocalDate;

public class TestConstants {
  public static final Long EXPENSE_ID = 1L;
  public static final Float EXPENSE_AMOUNT = 2.5F;
  public static final String EXPENSE_DESCRIPTION = "WATER";
  public static final LocalDate EXPENSE_DATE = LocalDate.now();
  public static final Long CATEGORY_ID = 2L;
  public static final String CATEGORY_NAME = "FOOD";
  public static final String CATEGORY_EXPENSE_TYPE = "EXPENSE";
  public static final Integer PAGE_NUMBER = 1;
  public static final Integer PAGE_SIZE = 10;
  public static final Integer PAGE_TOTAL_RESULTS = 100;
  public static final LocalDate START_DATE = LocalDate.now();
  public static final LocalDate END_DATE = LocalDate.now().plusDays(1);
}
