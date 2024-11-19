package com.payment.paymentIntegration.exception;

public class PaymentResponseNotFoundException extends RuntimeException {
    public PaymentResponseNotFoundException(String message) {
        super(message);
    }
}
