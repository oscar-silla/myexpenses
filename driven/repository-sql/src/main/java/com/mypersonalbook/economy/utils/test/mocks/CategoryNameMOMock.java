package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.CategoryNameMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_NAME_UPPER_CASE;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMOMock.CATEGORY_MO;
import static com.mypersonalbook.economy.utils.test.mocks.ids.CategoryNameIdMock.CATEGORY_NAME_ID;

public class CategoryNameMOMock {
  public static final CategoryNameMO CATEGORY_NAME_MO =
      new CategoryNameMO(CATEGORY_NAME_ID, CATEGORY_NAME_UPPER_CASE, CATEGORY_MO);
}
