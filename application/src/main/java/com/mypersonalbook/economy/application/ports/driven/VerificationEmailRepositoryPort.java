package com.mypersonalbook.economy.application.ports.driven;

import com.mypersonalbook.economy.domain.EmailCode;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface VerificationEmailRepositoryPort {
  boolean save(String email, UUID uuid, LocalDateTime creationDate);

  Optional<EmailCode> findByEmail(String email);
}
