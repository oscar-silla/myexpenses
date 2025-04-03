package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.models.VerificationEmailMO;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface VerificationEmailRepositoryMapper {
  default VerificationEmailMO toVerificationEmailMO(
      String email, UUID code, LocalDateTime creationDate) {
    return new VerificationEmailMO(email, code, creationDate);
  }

  EmailCode toEmailCode(VerificationEmailMO verificationEmailMO);
}
