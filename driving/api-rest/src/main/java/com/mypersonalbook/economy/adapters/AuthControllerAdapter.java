package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.auth.LoginUseCasePort;
import com.mypersonalbook.economy.application.ports.driving.auth.ResendVerificationCodeUseCasePort;
import com.mypersonalbook.economy.domain.User;
import com.mypersonalbook.economy.mappers.AuthControllerMapper;
import openapi.economy.api.AuthApi;
import openapi.economy.model.LoginRequestBodyType;
import openapi.economy.model.LoginResponseType;
import openapi.economy.model.ResendVerificationCodeRequestBodyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/economy/v1")
public class AuthControllerAdapter implements AuthApi {
  private final Logger logger = LoggerFactory.getLogger(AuthControllerAdapter.class);
  private final AuthControllerMapper authControllerMapper;
  private final LoginUseCasePort loginUseCase;
  private final ResendVerificationCodeUseCasePort resendVerificationCodeUseCase;

  AuthControllerAdapter(
      AuthControllerMapper authControllerMapper,
      LoginUseCasePort loginUseCase,
      ResendVerificationCodeUseCasePort resendVerificationCodeUseCase) {
    this.authControllerMapper = authControllerMapper;
    this.loginUseCase = loginUseCase;
    this.resendVerificationCodeUseCase = resendVerificationCodeUseCase;
  }

  @Override
  public ResponseEntity<LoginResponseType> postLogin(LoginRequestBodyType loginRequestBodyType) {
    logger.info("POST /economy/v1/login with body: {}", loginRequestBodyType);
    User user = this.authControllerMapper.toUser(loginRequestBodyType);
    String token = this.loginUseCase.execute(user);
    return ResponseEntity.ok(this.authControllerMapper.toLoginResponseType(token));
  }

  @Override
  public ResponseEntity<Void> resendVerificationCode(
      ResendVerificationCodeRequestBodyType resendVerificationCodeRequestBodyType) {
    logger.info("POST /economy/v1/resend-verification-code");
    User user = this.authControllerMapper.toUser(resendVerificationCodeRequestBodyType);
    this.resendVerificationCodeUseCase.execute(user.getEmail());
    return ResponseEntity.ok().build();
  }
}
