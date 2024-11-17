package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.client.RazorpayClient;
import com.payment.paymentIntegration.dto.RazorpayRequest;
import com.payment.paymentIntegration.dto.RazorpayResponse;
import com.payment.paymentIntegration.entity.Orders;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService; // Class under test

    @Mock
    private RazorpayClient razorpayClient; // Mocking the Feign client

    @Test
    void testCreatePaymentLink() {
        // Mock order and response
        Orders mockOrder = new Orders();
        mockOrder.setOrderId(1L);
        mockOrder.setAmount(1000);
        mockOrder.setName("Test User");
        mockOrder.setEmail("test@example.com");

        RazorpayResponse mockResponse = new RazorpayResponse();
        mockResponse.setId("payment_link_123");
        mockResponse.setStatus("created");

        // Mock RazorpayClient behavior
        when(razorpayClient.createPaymentLink(any(RazorpayRequest.class))).thenReturn(mockResponse);

        // Invoke the service method
        RazorpayResponse response = paymentService.createPaymentLink(mockOrder);

        // Assertions
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getId(), "Response ID should not be null");
        assertNotNull(response.getStatus(), "Response status should not be null");

        // Verify the interaction with RazorpayClient
        verify(razorpayClient, times(1)).createPaymentLink(any(RazorpayRequest.class));
    }
}
