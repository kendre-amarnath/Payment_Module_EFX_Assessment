package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.exception.PaymentResponseNotFoundException;
import com.payment.paymentIntegration.repository.PaymentResponseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentResponseServiceTest {

    @Mock
    private PaymentResponseRepository paymentResponseRepository;

    @InjectMocks
    private PaymentResponseService paymentResponseService;

    @Test
    void testSetPaymentDetails() {
        PaymentResponse paymentResponse = new PaymentResponse();
        when(paymentResponseRepository.save(paymentResponse)).thenReturn(paymentResponse);

        PaymentResponse result = paymentResponseService.setPaymentDetails(paymentResponse);
        assertNotNull(result);
    }

    @Test
    void testGetAllPaymentDetails() {
        PaymentResponse payment1 = new PaymentResponse();
        PaymentResponse payment2 = new PaymentResponse();
        when(paymentResponseRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        List<PaymentResponse> result = paymentResponseService.getAllPaymentDetails();
        assertEquals(2, result.size());
    }



    @Test

    void testGetPaymentDetailsByOrderIdNotFound() {
      when(paymentResponseRepository.findByOrderId(1L)).thenReturn(Optional.empty());

        boolean exceptionThrown = false;
        try {
            paymentResponseService.getPaymentDetailsByOrderId(1L);
        } catch (PaymentResponseNotFoundException e) {
            exceptionThrown = true; // Set to true if exception is thrown
        }

        assertTrue(exceptionThrown, "Expected PaymentResponseNotFoundException to be thrown");
    }


}
