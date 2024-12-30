package com.orderapi.order_api.config;

import com.orderapi.order_api.exceptions.GeneralServiceException;
import com.orderapi.order_api.exceptions.NoDataFoundException;
import com.orderapi.order_api.exceptions.ValidateServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request){
        log.info(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request){
        log.info(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
