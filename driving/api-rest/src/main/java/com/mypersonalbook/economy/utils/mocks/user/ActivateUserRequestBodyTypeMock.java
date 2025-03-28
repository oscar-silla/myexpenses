package com.mypersonalbook.economy.utils.mocks.user;

import openapi.economy.model.ActivateUserRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_CODE;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;

public class ActivateUserRequestBodyTypeMock {
  public static final ActivateUserRequestBodyType ACTIVATE_USER_REQUEST_BODY_TYPE =
      new ActivateUserRequestBodyType(USER_EMAIL, EMAIL_CODE);
}
