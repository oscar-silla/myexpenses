package com.mypersonalbook.economy.utils.test.mocks.ids;

import com.mypersonalbook.economy.models.ids.CategoryNameId;

import static com.mypersonalbook.economy.utils.test.TestConstants.CATEGORY_ID;
import static com.mypersonalbook.economy.utils.test.TestConstants.LOCALE_ES;

public class CategoryNameIdMock {
  public static final CategoryNameId CATEGORY_NAME_ID = new CategoryNameId(CATEGORY_ID, LOCALE_ES);
}
