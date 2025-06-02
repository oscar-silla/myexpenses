package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.CategoryMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.UserMOMock.USER_MO;

public class CategoryMOMock {
  public static final CategoryMO CATEGORY_MO =
      new CategoryMO(CATEGORY_ID, CATEGORY_NAME_UPPER_CASE, USER_MO, CATEGORY_COLOR);
}
