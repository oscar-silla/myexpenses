package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.EmailCode;
import com.mypersonalbook.economy.domain.User;
import openapi.economy.model.ActivateUserRequestBodyType;
import openapi.economy.model.UserActivateResponseType;
import openapi.economy.model.UserRequestBodyType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {
  User toUser(UserRequestBodyType userRequestBodyType);

  EmailCode toEmailCode(ActivateUserRequestBodyType activateUserRequestBodyType);

  default UserActivateResponseType toUserActivateResponseType(String token) {
    UserActivateResponseType userActivateResponseType = new UserActivateResponseType();
    userActivateResponseType.setToken(token);
    return userActivateResponseType;
  }
}
