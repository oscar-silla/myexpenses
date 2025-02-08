package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Category;

import java.util.Collections;
import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.LocaleNameMock.LOCALE_NAME;
import static com.mypersonalbook.economy.utils.test.mocks.LocaleNameMock.LOCALE_NAME_2;

public class CategoryMock {
  public static Category EXPENSE_CATEGORY =
      new Category(CATEGORY_ID, List.of(LOCALE_NAME), CATEGORY_EXPENSE_TRANSACTION_TYPE_UPPER_CASE);
  public static Category REVENUE_CATEGORY =
      new Category(CATEGORY_ID_2, List.of(LOCALE_NAME_2), CATEGORY_TRANSACTION_TYPE_2);
  public static Category EMPTY_CATEGORY = new Category(null, Collections.emptyList(), " ");
}
