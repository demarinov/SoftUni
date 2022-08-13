package com.dido.holidaybay.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NonUniqueResultException;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalConnectionExceptionHandler {

    @ExceptionHandler({ConnectException.class, WebClientRequestException.class})
    public ModelAndView handleConnectionExceptions(ConnectException e) {
        ModelAndView modelAndView = new ModelAndView("connection-error");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }
}
