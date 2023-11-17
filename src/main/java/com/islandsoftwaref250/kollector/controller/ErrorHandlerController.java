package com.islandsoftwaref250.kollector.controller;


import com.islandsoftwaref250.kollector.exceptions.DataNotFoundException;
import com.islandsoftwaref250.kollector.exceptions.ProductSaveException;
import com.islandsoftwaref250.kollector.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
        var errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductSaveException.class)
    public ResponseEntity<ErrorResponse> handleProductSaveException(ProductSaveException ex) {
        var errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
