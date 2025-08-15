package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildResponse("VERİ HATASI", "Veri bütünlüğü hatası", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ErrorResponse> handleSqlError(BadSqlGrammarException ex) {
        return buildResponse("VERİTABANI HATASI", "SQL sorgusunda hata", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return buildResponse("SUNUCU HATASI", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String error, String message, HttpStatus status) {
        return new ResponseEntity<>(
                new ErrorResponse(error, message, status.value(), LocalDateTime.now()),
                status
        );
    }
}
