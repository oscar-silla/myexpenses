package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.CategoryMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class CategoryMOMock {
  public static final CategoryMO CATEGORY_MO =
      new CategoryMO(CATEGORY_ID, CATEGORY_NAME, CATEGORY_EXPENSE_TYPE);
}
