package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.models.VerificationEmailMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_CODE;
import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_TO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VerificationEmailRepositoryMapperTest {
  VerificationEmailRepositoryMapper verificationEmailRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.verificationEmailRepositoryMapper = new VerificationEmailRepositoryMapperImpl();
  }

  @Test
  @DisplayName("Should map to verification email model")
  void shouldMapToVerificationEmailMO() {
    final VerificationEmailMO RESULT =
        this.verificationEmailRepositoryMapper.toVerificationEmailMO(EMAIL_TO, EMAIL_CODE);
    assertNotNull(RESULT);
    assertEquals(EMAIL_TO, RESULT.getEmail());
    assertEquals(EMAIL_CODE, RESULT.getCode());
  }
}
