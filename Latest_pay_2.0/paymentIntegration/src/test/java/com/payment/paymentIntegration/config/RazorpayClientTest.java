package com.payment.paymentIntegration.config;

import com.payment.paymentIntegration.client.RazorpayClient;
import com.payment.paymentIntegration.config.RazorpayClientConfig;
import com.payment.paymentIntegration.dto.RazorpayRequest;
import com.payment.paymentIntegration.dto.RazorpayResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RazorpayClientTest {

    @Mock
    RazorpayClient razorpayClient;

    @Test
    void testCreatePaymentLink() {
        RazorpayRequest mockRequest = new RazorpayRequest();
        RazorpayResponse mockResponse = new RazorpayResponse();
        mockResponse.setId("payment_link_123");

        when(razorpayClient.createPaymentLink(mockRequest)).thenReturn(mockResponse);

        RazorpayResponse response = razorpayClient.createPaymentLink(mockRequest);
        assertNotNull(response);
        assertNotNull(response.getId());
        verify(razorpayClient, times(1)).createPaymentLink(mockRequest);
    }
}
