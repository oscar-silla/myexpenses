package com.mypersonalbook.economy.handlers;

import com.mypersonalbook.economy.exceptions.BadRequestException;
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
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(RESULT.getBody()).getCode());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), Objects.requireNonNull(RESULT.getBody()).getMessage());
    }
}
