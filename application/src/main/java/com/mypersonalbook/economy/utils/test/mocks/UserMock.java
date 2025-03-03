package com.mypersonalbook.economy.utils.test.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

import com.mypersonalbook.economy.domain.User;

public class UserMock {
  public static final User USER =
          new User(USER_ID, USER_NAME, USER_FIRST_SURNAME, USER_SECOND_SURNAME, USER_EMAIL, USER_PASSWORD);
  public static final User USER_TO_ENCODE =
          new User(USER_ID, USER_NAME, USER_FIRST_SURNAME, USER_SECOND_SURNAME, USER_EMAIL, USER_PASSWORD);
  public static final User USER_WITHOUT_NAME =
      new User(USER_ID, null, USER_FIRST_SURNAME, USER_SECOND_SURNAME, USER_EMAIL, USER_PASSWORD);
  public static final User USER_WITHOUT_FIRST_SURNAME =
      new User(USER_ID, USER_NAME, null, USER_SECOND_SURNAME, USER_EMAIL, USER_PASSWORD);
  public static final User USER_WITHOUT_EMAIL =
      new User(USER_ID, USER_NAME, USER_FIRST_SURNAME, USER_SECOND_SURNAME, null, USER_PASSWORD);
  public static final User USER_WITHOUT_PASSWORD =
      new User(USER_ID, USER_NAME, USER_FIRST_SURNAME, USER_SECOND_SURNAME, USER_EMAIL, null);
}
