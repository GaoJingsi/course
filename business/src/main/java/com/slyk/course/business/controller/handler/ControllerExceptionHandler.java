package com.slyk.course.business.controller.handler;

import com.slyk.course.server.bo.ResponseBo;
import com.slyk.course.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseBo<?> validatorExceptionHandler(ValidatorException e) {
        return ResponseBo
                .builder()
                .error_no(50001)
                .msg(e.getMessage())
                .build();
    }

}
