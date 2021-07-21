package org.tonkushin.example.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tonkushin.example.exception.ItemNotFoundException;

@RestControllerAdvice
public class ItemNotFoundRestControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String itemNotFoundExceptionHandler(ItemNotFoundException e) {
        return e.getMessage();
    }
}
