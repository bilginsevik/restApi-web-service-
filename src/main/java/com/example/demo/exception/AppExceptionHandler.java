package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBranchNotFound(BranchNotFoundException ex) {
        return buildResponse("ŞUBE BULUNAMADI", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse("KULLANICI BULUNAMADI", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMenuItemNotFound(MenuItemNotFoundException ex) {
        return buildResponse("MENÜ ÖĞESİ BULUNAMADI", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex) {
        return buildResponse("SİPARİŞ BULUNAMADI", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderStatus(InvalidOrderStatusException ex) {
        return buildResponse("GEÇERSİZ SİPARİŞ DURUMU", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderItemsEmptyException.class)
    public ResponseEntity<ErrorResponse> handleOrderItemsEmpty(OrderItemsEmptyException ex) {
        return buildResponse("SİPARİŞ ÖĞELERİ BOŞ", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MinimumOrderAmountException.class)
    public ResponseEntity<ErrorResponse> handleMinimumOrderAmount(MinimumOrderAmountException ex) {
        return buildResponse("MİNİMUM SİPARİŞ TUTARI", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantClosedException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantClosed(RestaurantClosedException ex) {
        return buildResponse("RESTORAN KAPALI", ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String error, String message, HttpStatus status) {
        return new ResponseEntity<>(
                new ErrorResponse(error, message, status.value(), LocalDateTime.now()),
                status
        );
    }
}
