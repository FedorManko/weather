package com.andersen.weather.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.andersen.weather.dto.response.ErrorResponseDto;
import com.andersen.weather.enums.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    FieldError fieldError = ex.getFieldErrors().get(0);
    String message = fieldError.getDefaultMessage();
    return processError(BAD_REQUEST, message);
  }

  private ResponseEntity<ErrorResponseDto> processError(HttpStatus status, String message) {
    log.error(message);
    ErrorResponseDto error = buildErrorResponseDto(message);
    return ResponseEntity.status(status.value()).body(error);
  }

  private ErrorResponseDto buildErrorResponseDto(String message) {
    return ErrorResponseDto.builder()
        .code(ErrorCode.valueOfMessage(message))
        .message(message)
        .timestamp(Instant.now())
        .build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<ErrorResponseDto> handleMethodEntityNotFoundException(
      EntityNotFoundException ex) {
    String message = ex.getMessage();
    return processError(NOT_FOUND, message);
  }
}
