package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.User;
import openapi.economy.model.UserRequestBodyType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {
  User toUser(UserRequestBodyType userRequestBodyType);
}
