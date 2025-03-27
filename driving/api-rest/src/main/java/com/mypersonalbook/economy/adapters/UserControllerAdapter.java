package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.user.ActivateUserUseCasePort;
import com.mypersonalbook.economy.application.ports.driving.user.SaveUserUseCasePort;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.UserControllerMapper;
import openapi.economy.api.UsersApi;
import openapi.economy.model.ActivateUserRequestBodyType;
import openapi.economy.model.UserRequestBodyPatchType;
import openapi.economy.model.UserRequestBodyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/economy/v1")
public class UserControllerAdapter implements UsersApi {
  final Logger logger = LoggerFactory.getLogger(UserControllerAdapter.class);
  final UserControllerMapper userControllerMapper;
  final SaveUserUseCasePort saveUserUseCase;
  final ActivateUserUseCasePort activateUserUseCase;

  UserControllerAdapter(
      UserControllerMapper userControllerMapper,
      SaveUserUseCasePort saveUserUseCase,
      ActivateUserUseCasePort activateUserUseCase) {
    this.userControllerMapper = userControllerMapper;
    this.saveUserUseCase = saveUserUseCase;
    this.activateUserUseCase = activateUserUseCase;
  }

  @Override
  public ResponseEntity<Void> postUser(UserRequestBodyType userRequestBodyType) {
    logger.info("POST /economy/v1/users with body: {}", userRequestBodyType.toString());
    User user = this.userControllerMapper.toUser(userRequestBodyType);
    this.saveUserUseCase.execute(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> activateUser(
      ActivateUserRequestBodyType activateUserRequestBodyType) {
    logger.info(
        "POST /economy/v1/users/activate with body: {}", activateUserRequestBodyType.toString());
    this.activateUserUseCase.execute(
        this.userControllerMapper.toEmailVerification(activateUserRequestBodyType));
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> patchUser(
      Long id, UserRequestBodyPatchType userRequestBodyPatchType) {
    return null;
  }
}
