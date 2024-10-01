package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.ExpenseMO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMOMock.CATEGORY_MO;

public class ExpenseMOMock {
  public static final ExpenseMO EXPENSE_MO =
      new ExpenseMO(EXPENSE_ID_1, EXPENSE_AMOUNT, EXPENSE_DESCRIPTION, EXPENSE_DATE_1, CATEGORY_MO);
  public static final Page<ExpenseMO> EXPENSES_PAGE_MO =
      new PageImpl<ExpenseMO>(List.of(EXPENSE_MO));
}
