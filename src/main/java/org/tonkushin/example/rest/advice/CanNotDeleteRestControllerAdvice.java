package org.tonkushin.example.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tonkushin.example.exception.CanNotDeleteException;

@RestControllerAdvice
public class CanNotDeleteRestControllerAdvice {
    @ResponseBody
    @ExceptionHandler(CanNotDeleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String canNotDeleteExceptionHandler(CanNotDeleteException e) {
        return e.getMessage();
    }
}
