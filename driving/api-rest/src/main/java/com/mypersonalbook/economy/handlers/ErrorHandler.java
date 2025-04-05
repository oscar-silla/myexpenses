package com.mypersonalbook.economy.handlers;

import com.mypersonalbook.economy.application.exceptions.*;
import openapi.economy.model.ErrorResponseType;
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
    return ResponseEntity.status(exception.getStatusCode()).body(errorResponseType);
  }

  @ExceptionHandler(value = NotFoundException.class)
  protected ResponseEntity<ErrorResponseType> handleNotFound(NotFoundException exception) {
    ErrorResponseType errorResponseType = new ErrorResponseType();
    errorResponseType.setCode(exception.getStatusCode().value());
    errorResponseType.setMessage(exception.getStatusText());
    return ResponseEntity.status(exception.getStatusCode()).body(errorResponseType);
  }

  @ExceptionHandler(value = ConflictException.class)
  protected ResponseEntity<ErrorResponseType> handleConflict(ConflictException exception) {
    ErrorResponseType errorResponseType = new ErrorResponseType();
    errorResponseType.setCode(exception.getStatusCode().value());
    errorResponseType.setMessage(exception.getStatusText());
    return ResponseEntity.status(exception.getStatusCode()).body(errorResponseType);
  }

  @ExceptionHandler(value = UnauthorizedException.class)
  protected ResponseEntity<ErrorResponseType> handleUnauthorizedException(
      UnauthorizedException exception) {
    ErrorResponseType errorResponseType = new ErrorResponseType();
    errorResponseType.setCode(exception.getStatusCode().value());
    errorResponseType.setMessage(exception.getStatusText());
    return ResponseEntity.status(exception.getStatusCode()).body(errorResponseType);
  }

  @ExceptionHandler(TooManyRequestsException.class)
  protected ResponseEntity<ErrorResponseType> handleTooManyRequestsException(
      TooManyRequestsException exception) {
    ErrorResponseType errorResponseType = new ErrorResponseType();
    errorResponseType.setCode(exception.getStatusCode().value());
    errorResponseType.setMessage(exception.getStatusText());
    return ResponseEntity.status(exception.getStatusCode()).body(errorResponseType);
  }
}
