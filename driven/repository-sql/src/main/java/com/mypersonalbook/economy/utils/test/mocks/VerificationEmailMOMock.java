package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.VerificationEmailMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_CODE;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_EMAIL;

public class VerificationEmailMOMock {
  public static final VerificationEmailMO VERIFICATION_EMAIL_MO =
      new VerificationEmailMO(USER_EMAIL, EMAIL_CODE);
}
