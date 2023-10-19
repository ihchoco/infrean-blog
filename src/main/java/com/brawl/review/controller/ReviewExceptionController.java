package com.brawl.review.controller;

import com.brawl.review.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
//@ControllerAdvice
public class ReviewExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e){

        ErrorResponse error = new ErrorResponse("400", "에러입니다");

        List<FieldError> fieldErrors = e.getFieldErrors();
        fieldErrors.forEach(x -> {
            error.addValitationField(x.getField(), x.getDefaultMessage());
        });
        return error;
    }
}
