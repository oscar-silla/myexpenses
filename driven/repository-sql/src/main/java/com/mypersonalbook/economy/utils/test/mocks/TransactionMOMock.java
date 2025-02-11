package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.TransactionMO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.CategoryMOMock.CATEGORY_MO;
import static com.mypersonalbook.economy.utils.test.mocks.TransactionTypeMOMock.TRANSACTION_TYPE_MO;

public class TransactionMOMock {
  public static final TransactionMO TRANSACTION_MO =
      new TransactionMO(
          TRANSACTION_ID_1,
              EXPENSE_TRANSACTION_AMOUNT,
          TRANSACTION_DESCRIPTION,
          TRANSACTION_DATE_1,
          TRANSACTION_TYPE_MO,
          CATEGORY_MO);
  public static final Page<TransactionMO> EXPENSES_PAGE_MO =
      new PageImpl<TransactionMO>(List.of(TRANSACTION_MO));
}
