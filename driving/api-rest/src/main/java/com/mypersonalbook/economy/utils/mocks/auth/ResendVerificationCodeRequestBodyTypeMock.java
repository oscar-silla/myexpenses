package com.mypersonalbook.economy.utils.mocks.auth;

import openapi.economy.model.ResendVerificationCodeRequestBodyType;

import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;

public class ResendVerificationCodeRequestBodyTypeMock {
  public static ResendVerificationCodeRequestBodyType RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE() {
    final ResendVerificationCodeRequestBodyType RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE =
        new ResendVerificationCodeRequestBodyType();
    RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE.setEmail(USER_EMAIL);
    return RESEND_VERIFICATION_CODE_REQUEST_BODY_TYPE;
  }
}
