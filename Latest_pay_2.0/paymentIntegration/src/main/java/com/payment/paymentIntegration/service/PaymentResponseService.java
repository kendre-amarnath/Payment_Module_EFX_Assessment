// PaymentResponseService.java
package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.exception.PaymentResponseNotFoundException;
import com.payment.paymentIntegration.repository.PaymentResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentResponseService {

    @Autowired
    private PaymentResponseRepository paymentResponseRepository;

    public PaymentResponse setPaymentDetails(PaymentResponse paymentResponse) {
        Optional<PaymentResponse> response = paymentResponseRepository.findByOrderId(paymentResponse.getOrderId());
        response.ifPresent(value -> paymentResponse.setId(value.getId()));
        return paymentResponseRepository.save(paymentResponse);
    }

    public List<PaymentResponse> getAllPaymentDetails() {
        return paymentResponseRepository.findAll();
    }

    public PaymentResponse getPaymentDetailsByOrderId(Long orderId)
    {
        if(paymentResponseRepository.existsById(orderId))
        {
            return paymentResponseRepository.findByOrderId(orderId).get();
        }
        else{
           throw new PaymentResponseNotFoundException("Payment response for order ID " + orderId + " not found");
        }

    }

}
