package com.mypersonalbook.economy.utils.mocks.auth;

import openapi.economy.model.LoginRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_PASSWORD;

public class LoginRequestBodyTypeMock {
  public static LoginRequestBodyType LOGIN_REQUEST_BODY_TYPE() {
    final LoginRequestBodyType LOGIN_REQUEST_BODY_TYPE = new LoginRequestBodyType();
    LOGIN_REQUEST_BODY_TYPE.setEmail(USER_EMAIL);
    LOGIN_REQUEST_BODY_TYPE.setPassword(USER_PASSWORD);
    return LOGIN_REQUEST_BODY_TYPE;
  }
}
