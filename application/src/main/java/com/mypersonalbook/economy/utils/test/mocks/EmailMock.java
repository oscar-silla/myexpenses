package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.domain.Email;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class EmailMock {
  public static final Email EMAIL = new Email(EMAIL_FROM, EMAIL_TO, EMAIL_SUBJECT, EMAIL_TEXT);
}
