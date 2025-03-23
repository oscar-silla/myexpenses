package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.mappers.VerificationEmailRepositoryMapper;
import com.mypersonalbook.economy.models.VerificationEmailMO;
import com.mypersonalbook.economy.repositories.VerificationEmailJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_CODE;
import static com.mypersonalbook.economy.utils.test.TestConstants.EMAIL_TO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VerificationEmailRepositoryAdapterTest {
  VerificationEmailRepositoryAdapter verificationEmailRepositoryAdapter;

  @Mock private VerificationEmailJpaRepository verificationEmailJpaRepository;
  @Mock private VerificationEmailRepositoryMapper verificationEmailRepositoryMapper;

  @BeforeEach
  void setUp() {
    this.verificationEmailRepositoryAdapter =
        new VerificationEmailRepositoryAdapter(
            this.verificationEmailJpaRepository, this.verificationEmailRepositoryMapper);
  }

  @Test
  @DisplayName("Should return true when save")
  void shouldReturnTrue_WhenSave() {
    when(this.verificationEmailRepositoryMapper.toVerificationEmailMO(any(), any()))
        .thenReturn(new VerificationEmailMO());
    when(this.verificationEmailJpaRepository.save(any(VerificationEmailMO.class)))
        .thenReturn(new VerificationEmailMO());
    final boolean RESULT = this.verificationEmailRepositoryAdapter.save(EMAIL_TO, EMAIL_CODE);
    assertTrue(RESULT);
  }
}
