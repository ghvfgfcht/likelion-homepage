package com.homepage.likelion.util.exception;

import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

// @ControllerAdvice 어노테이션이 붙은 클래스는 전역적으로 모든 컨트롤러의 예외처리를 담당하게 된다.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Method name : "handle" + "${error name}"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        // errorMessage를 ResponseEntity에 담아주어야 한다.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), errorMessage));
    }

    // javax.validation.ConstraintViolationException package 내에서 발생하는 Error Handler
    // ConstraintViolationException 은 RuntimeException을 상속하는 unhandled exception이다.
    // 데이터베이스에서 발생하는 예외 중 하나로, 제약 조건이 위배되었을 때 발생하는 예외이다.
    // ex)_ DB의 Unique field 에 중복된 값을 insert 하려고 할 때
    // ex)_ FK 제약조건을 위반하여 참조할 수 없는 값을 참조하려고 할 때
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e){
        String errorMassage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), errorMassage));
    }
}