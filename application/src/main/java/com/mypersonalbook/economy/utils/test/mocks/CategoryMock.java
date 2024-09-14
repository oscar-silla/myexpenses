package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Category;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class CategoryMock {
  public static Category EXPENSE_CATEGORY =
      new Category(CATEGORY_ID, CATEGORY_NAME, CATEGORY_EXPENSE_TYPE);
  public static Category EMPTY_CATEGORY =
          new Category(null, " ", " ");
}
