package com.mypersonalbook.economy.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class TooManyRequestsException extends HttpStatusCodeException {
  public TooManyRequestsException() {
    super(HttpStatus.TOO_MANY_REQUESTS, HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
  }
}
