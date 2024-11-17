package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.repository.PaymentResponseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentResponseServiceTest {

    @InjectMocks
    private PaymentResponseService paymentResponseService;

    @Mock
    private PaymentResponseRepository paymentResponseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetPaymentDetails() {
        PaymentResponse mockPaymentResponse = new PaymentResponse(); // Set fields
        when(paymentResponseRepository.save(mockPaymentResponse)).thenReturn(mockPaymentResponse);

        PaymentResponse savedResponse = paymentResponseService.setPaymentDetails(mockPaymentResponse);

        assertEquals(mockPaymentResponse, savedResponse);
        verify(paymentResponseRepository, times(1)).save(mockPaymentResponse);
    }

    @Test
    void testGetAllPaymentDetails() {
        List<PaymentResponse> mockResponses = Arrays.asList(new PaymentResponse(), new PaymentResponse());
        when(paymentResponseRepository.findAll()).thenReturn(mockResponses);

        List<PaymentResponse> responses = paymentResponseService.getAllPaymentDetails();

        assertEquals(mockResponses, responses);
        verify(paymentResponseRepository, times(1)).findAll();
    }
//
//    @Test
//    void testGetPaymentDetailsByOrderId() {
//        PaymentResponse mockResponse = mock(PaymentResponse.class); // Mock the PaymentResponse object
//        when(paymentResponseRepository.findByOrderId(123L))
//                .thenReturn(of(mockResponse));
//        Optional<PaymentResponse> response = paymentResponseService.getPaymentDetailsByOrderId(123L);
//        assertEquals(of(mockResponse), response);
//        verify(paymentResponseRepository, times(1)).findByOrderId(123L);
//    }

}
