package edu.odtu.ceng453.group10.catanbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnauthorizedLoginException extends RuntimeException {
  public UnauthorizedLoginException(String message) {
    super(message);
  }
}
