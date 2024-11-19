package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.service.PaymentResponseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentResponseControllerTest {

    @InjectMocks
    private PaymentResponseController paymentResponseController;

    @Mock
    private PaymentResponseService paymentResponseService;

    public PaymentResponseControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetPaymentDetails() {
        PaymentResponse mockPaymentResponse = new PaymentResponse();
        when(paymentResponseService.setPaymentDetails(any(PaymentResponse.class))).thenReturn(mockPaymentResponse);

        ResponseEntity<PaymentResponse> response = paymentResponseController.setPaymentDetails(mockPaymentResponse);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockPaymentResponse, response.getBody());
        verify(paymentResponseService, times(1)).setPaymentDetails(mockPaymentResponse);
    }

    @Test
    void testGetAllPaymentDetails() {
        List<PaymentResponse> mockResponses = Arrays.asList(new PaymentResponse(), new PaymentResponse());
        when(paymentResponseService.getAllPaymentDetails()).thenReturn(mockResponses);

        ResponseEntity<List<PaymentResponse>> response = paymentResponseController.getAllPaymentDetails();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponses, response.getBody());

        verify(paymentResponseService, times(1)).getAllPaymentDetails();
    }

    @Test
    void testGetPaymentDetailsByOrderId() {
        PaymentResponse mockResponse = new PaymentResponse(); // Add fields
        when(paymentResponseService.getPaymentDetailsByOrderId(123L)).thenReturn(mockResponse);

        ResponseEntity<PaymentResponse> response = paymentResponseController.getPaymentDetailsByOrderId(123L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
        verify(paymentResponseService, times(1)).getPaymentDetailsByOrderId(123L);
    }

}
