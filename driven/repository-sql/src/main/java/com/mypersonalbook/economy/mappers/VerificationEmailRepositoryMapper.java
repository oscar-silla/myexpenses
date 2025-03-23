package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.models.VerificationEmailMO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface VerificationEmailRepositoryMapper {
  default VerificationEmailMO toVerificationEmailMO(String email, UUID code) {
    return new VerificationEmailMO(email, code);
  }
}
