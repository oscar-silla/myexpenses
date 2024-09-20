package com.mypersonalbook.economy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NotFoundException extends HttpStatusCodeException {
  public NotFoundException() {
    super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
  }
}
