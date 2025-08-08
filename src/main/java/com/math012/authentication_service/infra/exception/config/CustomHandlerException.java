package com.math012.authentication_service.infra.exception.config;

import com.math012.authentication_service.infra.exception.EmailAlreadyRegisteredException;
import com.math012.authentication_service.infra.exception.InvalidFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<StructException> handlerEmailAlreadyRegisteredException(Exception e, WebRequest request){
        StructException exception = new StructException(new Date(), e.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<StructException> handlerInvalidFieldsException(Exception e, WebRequest request){
        StructException exception = new StructException(new Date(), e.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StructException> handlerBadCredentialsException(Exception e, WebRequest request){
        StructException exception = new StructException(new Date(), "Credenciais inválidas", request.getDescription(true));
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }
}