package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.client.RazorpayClient;
import com.payment.paymentIntegration.dto.RazorpayRequest;
import com.payment.paymentIntegration.dto.RazorpayResponse;
import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.exception.PaymentLinkCreationException;
import com.payment.paymentIntegration.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
public class PaymentService {

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private OrderRepository orderRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();

    private String generateRandomPrefix() {
        StringBuilder prefix = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(CHARACTERS.length());
            prefix.append(CHARACTERS.charAt(index));
        }
        return prefix.toString();
    }

    public RazorpayResponse createPaymentLink(Orders ord) {
        try {
            String phoneNumber = ord.getContact();
            String name = ord.getName();

            RazorpayRequest request = new RazorpayRequest();
            request.setAmount(ord.getAmount() * 100);
            request.setExpire_by(Instant.now().getEpochSecond() + 45 * 60);

            String randomPrefix = generateRandomPrefix();
            request.setReference_id(randomPrefix + ord.getOrderId());

            RazorpayRequest.Customer customer = new RazorpayRequest.Customer();
            customer.setName(name);
            customer.setContact("+91" + phoneNumber);
            customer.setEmail(ord.getEmail());
            request.setCustomer(customer);

            request.setDescription("Order Payment Link");

            return razorpayClient.createPaymentLink(request);
        } catch (Exception ex) {
            throw new PaymentLinkCreationException("Failed to create payment link: " + ex.getMessage());
        }
    }
}
