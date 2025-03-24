package com.mypersonalbook.economy.utils.test.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static com.mypersonalbook.economy.utils.test.mocks.RoleMock.USER_ROLE;

import com.mypersonalbook.economy.models.UserMO;
import java.util.ArrayList;

public class UserMOMock {
  public static final UserMO USER_MO =
      new UserMO(
          USER_ID,
          USER_NAME,
          USER_FIRST_SURNAME,
          USER_SECOND_SURNAME,
          USER_EMAIL,
          USER_PASSWORD,
          USER_ROLE,
          new ArrayList<>(),
          USER_IS_VERIFIED);
}
