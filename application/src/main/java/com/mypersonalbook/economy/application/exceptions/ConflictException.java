package com.mypersonalbook.economy.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ConflictException extends HttpStatusCodeException {
    public ConflictException() {
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase());
    }
}
