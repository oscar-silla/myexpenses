package com.mypersonalbook.economy.config.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthException extends AuthenticationException {
  public JwtAuthException(String msg) {
    super(msg);
  }
}
