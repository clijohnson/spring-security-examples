package com.hashedin.domain.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { NoSuchElementException.class })
  protected ResponseEntity<Object> handleNoElementFound(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Entity not found";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
  }
}