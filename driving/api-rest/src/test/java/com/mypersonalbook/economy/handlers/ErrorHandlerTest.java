package com.mypersonalbook.economy.handlers;

import com.mypersonalbook.economy.application.exceptions.*;
import openapi.economy.model.ErrorResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ErrorHandlerTest {
  ErrorHandler errorHandler;

  @BeforeEach
  void setUp() {
    this.errorHandler = new ErrorHandler();
  }

  @Test
  @DisplayName("Should return bad request error type")
  void shouldReturnBadRequestErrorType() {
    final BadRequestException EXCEPTION = new BadRequestException();
    final ResponseEntity<ErrorResponseType> RESULT = this.errorHandler.handleBadRequest(EXCEPTION);
    assertEquals(
        HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return not found error type")
  void shouldReturnNotFoundErrorType() {
    final NotFoundException EXCEPTION = new NotFoundException();
    final ResponseEntity<ErrorResponseType> RESULT = this.errorHandler.handleNotFound(EXCEPTION);
    assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return conflict error type")
  void shouldReturnConflictErrorType() {
    final ConflictException EXCEPTION = new ConflictException();
    final ResponseEntity<ErrorResponseType> RESULT = this.errorHandler.handleConflict(EXCEPTION);
    assertEquals(HttpStatus.CONFLICT.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.CONFLICT.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return unauthorized exception error type")
  void shouldReturnUnauthorizedExceptionErrorType() {
    final UnauthorizedException EXCEPTION = new UnauthorizedException();
    final ResponseEntity<ErrorResponseType> RESULT =
        this.errorHandler.handleUnauthorizedException(EXCEPTION);
    assertEquals(
        HttpStatus.UNAUTHORIZED.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return too many requests exception error type")
  void shouldReturnTooManyRequestsExceptionErrorType() {
    final TooManyRequestsException EXCEPTION = new TooManyRequestsException();
    final ResponseEntity<ErrorResponseType> RESULT =
            this.errorHandler.handleTooManyRequestsException(EXCEPTION);
    assertEquals(
            HttpStatus.TOO_MANY_REQUESTS.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
            HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(),
            Objects.requireNonNull(RESULT.getBody()).getMessage());
  }
}
