package com.mypersonalbook.economy.handlers;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.exceptions.UnauthorizedException;
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
  void shouldNotFoundErrorType() {
    final NotFoundException EXCEPTION = new NotFoundException();
    final ResponseEntity<ErrorResponseType> RESULT = this.errorHandler.handleNotFound(EXCEPTION);
    assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return conflict error type")
  void shouldConflictErrorType() {
    final ConflictException EXCEPTION = new ConflictException();
    final ResponseEntity<ErrorResponseType> RESULT = this.errorHandler.handleConflict(EXCEPTION);
    assertEquals(HttpStatus.CONFLICT.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.CONFLICT.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }

  @Test
  @DisplayName("Should return exception error type")
  void shouldExceptionErrorType() {
    final UnauthorizedException EXCEPTION = new UnauthorizedException();
    final ResponseEntity<ErrorResponseType> RESULT =
        this.errorHandler.handleUnauthorizedException(EXCEPTION);
    assertEquals(
        HttpStatus.UNAUTHORIZED.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
    assertEquals(
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        Objects.requireNonNull(RESULT.getBody()).getMessage());
  }
}
