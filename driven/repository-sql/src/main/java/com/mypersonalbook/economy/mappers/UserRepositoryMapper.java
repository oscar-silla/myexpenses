package com.mypersonalbook.economy.mappers;

import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.models.UserMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRepositoryMapper {
    UserMO toUserMO(User user);

    User toUser(UserMO userMO);
}
