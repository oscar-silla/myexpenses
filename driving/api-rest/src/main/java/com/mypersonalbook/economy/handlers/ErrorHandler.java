package com.mypersonalbook.economy.handlers;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import openapi.economy.model.ErrorResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = BadRequestException.class)
  protected ResponseEntity<ErrorResponseType> handleBadRequest(BadRequestException exception) {
    ErrorResponseType errorResponseType = new ErrorResponseType();
    errorResponseType.setCode(exception.getStatusCode().value());
    errorResponseType.setMessage(exception.getStatusText());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseType);
  }
}
