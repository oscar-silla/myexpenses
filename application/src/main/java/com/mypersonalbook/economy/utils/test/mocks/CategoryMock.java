package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Category;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class CategoryMock {
  public static Category EXPENSE_CATEGORY =
      new Category(CATEGORY_ID, CATEGORY_NAME, CATEGORY_TRANSACTION_TYPE);
  public static Category EXPENSE_CATEGORY_2 =
          new Category(CATEGORY_ID_2, CATEGORY_NAME_2, CATEGORY_TRANSACTION_TYPE_2);
  public static Category EMPTY_CATEGORY =
          new Category(null, " ", " ");
}
