package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.client.RazorpayClient;
import com.payment.paymentIntegration.dto.RazorpayRequest;
import com.payment.paymentIntegration.dto.RazorpayResponse;
import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.exception.PaymentLinkCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private RazorpayClient razorpayClient;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testCreatePaymentLink() {
        Orders order = new Orders();
        order.setAmount(500);

        RazorpayResponse expectedResponse = new RazorpayResponse();
        when(razorpayClient.createPaymentLink(any(RazorpayRequest.class))).thenReturn(expectedResponse);

        RazorpayResponse response = paymentService.createPaymentLink(order);
        assertNotNull(response);
    }

    @Test
    void testCreatePaymentLinkException() {
        Orders order = new Orders();
        order.setAmount(500);

        when(razorpayClient.createPaymentLink(any(RazorpayRequest.class))).thenThrow(new RuntimeException());

        assertThrows(PaymentLinkCreationException.class, () -> paymentService.createPaymentLink(order));
    }
}
