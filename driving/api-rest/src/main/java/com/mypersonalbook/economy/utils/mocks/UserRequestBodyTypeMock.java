package com.mypersonalbook.economy.utils.mocks;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

import openapi.economy.model.UserRequestBodyType;

public class UserRequestBodyTypeMock {
  public static UserRequestBodyType USER_REQUEST_BODY_TYPE() {
    UserRequestBodyType userRequestBodyType = new UserRequestBodyType();
    userRequestBodyType.setName(USER_NAME);
    userRequestBodyType.setFirstSurname(USER_FIRST_SURNAME);
    userRequestBodyType.setSecondSurname(USER_SECOND_SURNAME);
    userRequestBodyType.setEmail(USER_EMAIL);
    userRequestBodyType.setPassword(USER_PASSWORD);
    return userRequestBodyType;
  }
}
