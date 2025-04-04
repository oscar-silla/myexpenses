package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driven.VerificationEmailRepositoryPort;
import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.mappers.VerificationEmailRepositoryMapper;
import com.mypersonalbook.economy.repositories.VerificationEmailJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationEmailRepositoryAdapter implements VerificationEmailRepositoryPort {
  private final VerificationEmailJpaRepository verificationEmailJpaRepository;
  private final VerificationEmailRepositoryMapper verificationEmailRepositoryMapper;

  public VerificationEmailRepositoryAdapter(
      VerificationEmailJpaRepository verificationEmailJpaRepository,
      VerificationEmailRepositoryMapper verificationEmailRepositoryMapper) {
    this.verificationEmailJpaRepository = verificationEmailJpaRepository;
    this.verificationEmailRepositoryMapper = verificationEmailRepositoryMapper;
  }

  @Override
  public boolean save(String email, UUID uuid, LocalDateTime creationDate) {
    this.verificationEmailJpaRepository.save(
        this.verificationEmailRepositoryMapper.toVerificationEmailMO(email, uuid, creationDate));
    return true;
  }

  @Override
  public Optional<EmailCode> findByEmail(String email) {
    return this.verificationEmailJpaRepository
        .findById(email)
        .map(this.verificationEmailRepositoryMapper::toEmailCode);
  }
}
