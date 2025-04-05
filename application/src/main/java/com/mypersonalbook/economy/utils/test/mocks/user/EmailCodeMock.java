package com.mypersonalbook.economy.utils.test.mocks.user;

import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.utils.test.TestConstants;

import static com.mypersonalbook.economy.utils.test.TestConstants.LOCAL_DATE_TIME;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;

public class EmailCodeMock {
  public static EmailCode EMAIL_CODE =
      new EmailCode(USER_EMAIL, TestConstants.EMAIL_CODE, LOCAL_DATE_TIME);
  public static EmailCode EMAIL_CODE_WITH_1_PLUS_MINUTE =
          new EmailCode(USER_EMAIL, TestConstants.EMAIL_CODE, LOCAL_DATE_TIME.plusMinutes(1));
}
