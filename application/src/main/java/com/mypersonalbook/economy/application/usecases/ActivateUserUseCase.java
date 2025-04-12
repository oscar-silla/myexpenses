package com.mypersonalbook.economy.application.usecases;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.ports.driving.user.ActivateUserUseCasePort;
import com.mypersonalbook.economy.application.services.EmailService;
import com.mypersonalbook.economy.application.services.UserService;
import com.mypersonalbook.economy.config.auth.JwtUtil;
import com.mypersonalbook.economy.domain.EmailCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ActivateUserUseCase implements ActivateUserUseCasePort {
  private final EmailService emailService;
  private final UserService userService;
  private final JwtUtil jwtUtil;

  public ActivateUserUseCase(EmailService emailService, UserService userService, JwtUtil jwtUtil) {
    this.emailService = emailService;
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public String execute(EmailCode emailCode) {
    if (!this.emailService.validate(emailCode.getEmail())
        || !this.emailService.verifyEmailCode(emailCode)) {
      throw new BadRequestException();
    }
    this.userService.activateUserByEmail(emailCode.getEmail());
    return this.userService
        .findByEmail(emailCode.getEmail())
        .map(
            user -> {
              UserDetails userDetails = this.userService.loadUserByUsername(user.getEmail());
              return this.jwtUtil.generateToken(userDetails);
            })
        .orElseThrow(NotFoundException::new);
  }
}
