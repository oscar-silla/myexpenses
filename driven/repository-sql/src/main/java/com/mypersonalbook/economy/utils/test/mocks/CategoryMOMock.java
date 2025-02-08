package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.CategoryMO;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryNameMOMock.CATEGORY_NAME_MO;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionTypeMOMock.TRANSACTION_TYPE_MO;

public class CategoryMOMock {
  public static final CategoryMO CATEGORY_MO =
      new CategoryMO(CATEGORY_ID, TRANSACTION_TYPE_MO, List.of(CATEGORY_NAME_MO));
}
