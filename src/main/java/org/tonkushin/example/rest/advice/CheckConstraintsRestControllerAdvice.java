package org.tonkushin.example.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tonkushin.example.exception.CheckConstraintsException;

@RestControllerAdvice
public class CheckConstraintsRestControllerAdvice {
    @ResponseBody
    @ExceptionHandler(CheckConstraintsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String itemNotFoundExceptionHandler(CheckConstraintsException e) {
        return e.getMessage();
    }
}
