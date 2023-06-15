package br.com.phoneRecharges.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RechargeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RechargeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundHandler(RechargeNotFoundException ex) {
        return ex.getMessage();
    }

}
