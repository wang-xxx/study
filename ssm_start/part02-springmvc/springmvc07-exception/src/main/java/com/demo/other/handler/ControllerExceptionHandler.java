package com.demo.other.handler;

import com.demo.other.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public Result handleArithmeticException(ArithmeticException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public Result handleClassNotFoundException(ClassNotFoundException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleClassNotFoundException(Exception e) {
        return Result.fail(e.getMessage());
    }

}
