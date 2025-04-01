package com.mypersonalbook.economy.utils.test.mocks.user;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.RoleMock.USER_ROLE;

import com.mypersonalbook.economy.domain.User;

public class UserMock {
  public static final User USER =
      new User(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
  public static final User USER_NOT_VERIFIED =
      new User(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_FALSE);
  public static final User USER_TO_ENCODE =
      new User(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
  public static final User USER_WITHOUT_NAME =
      new User(
          USER_ID,
          null,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
  public static final User USER_WITHOUT_FIRST_SURNAME =
      new User(
          USER_ID,
          USER_NAME,
          null,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
  public static final User USER_WITHOUT_EMAIL =
      new User(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          null,
          USER_PASSWORD,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
  public static final User USER_WITHOUT_PASSWORD =
      new User(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          null,
          USER_ROLE,
          USER_IS_VERIFIED_TRUE);
}
