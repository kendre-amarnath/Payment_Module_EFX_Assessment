package com.payment.paymentIntegration.exception;

public class GlobalExceptionHandler {
    public GlobalExceptionHandler(String message) {
        System.out.println("Error: " + message);
    }

    public void handleException(Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }
}
