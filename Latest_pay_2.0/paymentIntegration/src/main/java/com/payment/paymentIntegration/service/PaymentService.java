package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.client.RazorpayClient;
import com.payment.paymentIntegration.dto.RazorpayRequest;
import com.payment.paymentIntegration.dto.RazorpayResponse;
import com.payment.paymentIntegration.entity.Orders;
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

    // Method to generate random three-character prefix for always giving the
    private String generateRandomPrefix(int length) {
        StringBuilder prefix = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            prefix.append(CHARACTERS.charAt(index));
        }
        return prefix.toString();
    }

    public RazorpayResponse createPaymentLink(Orders ord) {

        String phoneNumber = ord.getContact();
        String name = ord.getName();

        RazorpayRequest request = new RazorpayRequest();
        request.setAmount(ord.getAmount() * 100);
        request.setExpire_by(Instant.now().getEpochSecond() + 45 * 60); // Expiry time is 45 minutes from now

        // Generate unique reference ID with random prefix
        String randomPrefix = generateRandomPrefix(3);
        request.setReference_id(randomPrefix + ord.getOrderId());

        RazorpayRequest.Customer customer = new RazorpayRequest.Customer();
        customer.setName(name);
        customer.setContact("+91" + phoneNumber);
        customer.setEmail(ord.getEmail());
        request.setCustomer(customer);

        request.setDescription("-----------Order Payment Link-----------");

        return razorpayClient.createPaymentLink(request);
    }
}
