package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.dto.RazorpayResponse;
import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    public PaymentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePaymentLink() {
        Orders mockOrder = new Orders(); // Add fields
        RazorpayResponse mockResponse = new RazorpayResponse(); // Add fields
        when(paymentService.createPaymentLink(mockOrder)).thenReturn(mockResponse);

        ResponseEntity<RazorpayResponse> response = paymentController.createPaymentLink(mockOrder);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
        verify(paymentService, times(1)).createPaymentLink(mockOrder);
    }
}
