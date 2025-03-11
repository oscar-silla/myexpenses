package com.mypersonalbook.economy.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class UnauthorizedException extends HttpStatusCodeException {
  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
  }
}
