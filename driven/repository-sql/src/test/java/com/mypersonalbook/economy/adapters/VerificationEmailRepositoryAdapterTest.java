package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.mappers.VerificationEmailRepositoryMapper;
import com.mypersonalbook.economy.models.VerificationEmailMO;
import com.mypersonalbook.economy.repositories.VerificationEmailJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
    when(this.verificationEmailRepositoryMapper.toVerificationEmailMO(any(), any(), any()))
        .thenReturn(new VerificationEmailMO());
    when(this.verificationEmailJpaRepository.save(any(VerificationEmailMO.class)))
        .thenReturn(new VerificationEmailMO());
    final boolean RESULT =
        this.verificationEmailRepositoryAdapter.save(EMAIL_TO, EMAIL_CODE, LOCAL_DATE_TIME);
    assertTrue(RESULT);
  }

  @Test
  @DisplayName("Should return email code when find by email")
  void shouldReturnEmailCode_WhenFindByEmail() {
    when(this.verificationEmailRepositoryMapper.toEmailCode(any(VerificationEmailMO.class)))
        .thenReturn(new EmailCode());
    when(this.verificationEmailJpaRepository.findById(anyString()))
        .thenReturn(Optional.of(new VerificationEmailMO()));
    this.verificationEmailRepositoryAdapter.findByEmail(USER_EMAIL);
    verify(this.verificationEmailJpaRepository).findById(anyString());
  }
}
