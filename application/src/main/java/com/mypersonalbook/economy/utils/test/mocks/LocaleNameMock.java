package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.LocaleName;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class LocaleNameMock {
  public static final LocaleName LOCALE_NAME = new LocaleName(CATEGORY_NAME_UPPER_CASE, LOCALE_ES);
  public static final LocaleName LOCALE_NAME_2 = new LocaleName(CATEGORY_NAME_2, LOCALE_ES);
}
