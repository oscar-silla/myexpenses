package com.mypersonalbook.economy.utils.mocks.auth;

import openapi.economy.model.LoginResponseType;

import static com.mypersonalbook.economy.utils.test.TestConstants.TOKEN;

public class LoginResponseTypeMock {
  public static LoginResponseType LOGIN_RESPONSE_TYPE() {
    final LoginResponseType loginResponseType = new LoginResponseType();
    loginResponseType.setToken(TOKEN);
    return loginResponseType;
  }
}
