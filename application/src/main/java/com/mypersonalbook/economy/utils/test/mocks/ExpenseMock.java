package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMock.*;

public class ExpenseMock {
  public static final Expense EXPENSE =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_AMOUNT =
      new Expense(EXPENSE_ID, null, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_CATEGORY =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, null, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_EMPTY_CATEGORY =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EMPTY_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_EMPTY_DESCRIPTION =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, "  ", EXPENSE_DATE);
  public static final Expense EXPENSE_WITH_NULL_DATE =
      new Expense(EXPENSE_ID, EXPENSE_AMOUNT, EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, null);
  public static final Expense OTHER_EXPENSE =
      new Expense(
          EXPENSE_ID_2,
          EXPENSE_AMOUNT_2,
          EXPENSE_CATEGORY_2,
          EXPENSE_DESCRIPTION_2,
          EXPENSE_DATE_2);
  public static final Page<Expense> EXPENSES_PAGE = new PageImpl<>(List.of(EXPENSE));
}
