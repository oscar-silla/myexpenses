package com.mypersonalbook.economy.utils.test.mocks;

import com.mypersonalbook.economy.models.VerificationEmailMO;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;

public class VerificationEmailMOMock {
  public static final VerificationEmailMO VERIFICATION_EMAIL_MO =
      new VerificationEmailMO(USER_EMAIL, EMAIL_CODE, LOCAL_DATE_TIME);
}
