package com.brawl.controller;

import com.brawl.exception.HodologException;
import com.brawl.exception.InvalidRequest;
import com.brawl.exception.PostNotFound;
import com.brawl.response.ErrorResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 반환하는 HTTP 코드 상태
    @ExceptionHandler(MethodArgumentNotValidException.class) // 받아들이는 Exception
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e){
//        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for(FieldError fieldError : e.getFieldErrors()){
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(HodologException.class) // 받아들이는 Exception
    public ResponseEntity<ErrorResponse> HodologException(HodologException e){
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();


        /** 아주 간단하다
         * ResponseEntity<반환클래스> 사용법
         *  - ResponseEntity.status(int타입 - 스테이터스코드).body(내용부분-반환클래스객체)
         *  이렇게 하면 Http Header의 코드도 챙기고, Body 부분도 챙길 수 있다(1석 2조)
         */
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }

}


