package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.User;
import openapi.economy.model.LoginRequestBodyType;
import openapi.economy.model.LoginResponseType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
  User toUser(LoginRequestBodyType loginRequestBodyType);

  @Mapping(target = "token", source = "token")
  LoginResponseType toLoginResponseType(String token);
}
