package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Category;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class CategoryMock {
  public static Category EXPENSE_CATEGORY =
      new Category(CATEGORY_ID, CATEGORY_NAME_UPPER_CASE, CATEGORY_COLOR);
  public static Category EXPENSE_CATEGORY_2 =
      new Category(CATEGORY_ID_2, CATEGORY_NAME_UPPER_CASE_2, CATEGORY_COLOR_2);
  public static Category REVENUE_CATEGORY =
      new Category(CATEGORY_ID_2, CATEGORY_NAME_UPPER_CASE_2, CATEGORY_COLOR_2);
  public static Category EMPTY_CATEGORY = new Category(null, " ", "");
}
