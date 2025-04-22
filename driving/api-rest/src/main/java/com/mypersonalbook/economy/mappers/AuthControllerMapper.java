package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.User;
import openapi.economy.model.LoginRequestBodyType;
import openapi.economy.model.LoginResponseType;
import openapi.economy.model.ResendVerificationCodeRequestBodyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
  User toUser(LoginRequestBodyType loginRequestBodyType);

  User toUser(ResendVerificationCodeRequestBodyType resendVerificationCodeRequestBodyType);

  @Mapping(target = "token", source = "token")
  LoginResponseType toLoginResponseType(String token);
}
