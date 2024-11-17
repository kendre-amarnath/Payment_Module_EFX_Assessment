// PaymentResponseController.java
package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.service.PaymentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments/response")
@CrossOrigin("http://localhost:4200/")
public class PaymentResponseController {

    @Autowired
    private PaymentResponseService paymentResponseService;

//    // Endpoint to save payment details
//    @PostMapping
//    public ResponseEntity<PaymentResponse> setPaymentDetails(@RequestBody PaymentResponse paymentResponse) {
//        PaymentResponse savedResponse = paymentResponseService.setPaymentDetails(paymentResponse);
//        return ResponseEntity.created(savedResponse);
//    }

    @PostMapping
    public ResponseEntity<PaymentResponse> setPaymentDetails (@RequestBody PaymentResponse paymentResponse)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body(paymentResponseService.setPaymentDetails(paymentResponse));
    }


    // Endpoint to get all payment details
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPaymentDetails() {
        List<PaymentResponse> paymentResponses = paymentResponseService.getAllPaymentDetails();
        return ResponseEntity.ok(paymentResponses);
    }

    // Endpoint to get payment details by orderId
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId) {
        PaymentResponse paymentResponse = paymentResponseService.getPaymentDetailsByOrderId(orderId).get();
        if(paymentResponse == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
        }
    }

    // Endpoint to get payment details by paymentId
    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByPaymentId(@PathVariable("orderId") Long orderId) {
        // Log the incoming request for debugging
        System.out.println("Received request for payment ID: " + orderId);

        // Fetch the payment details using the service layer
        Optional<PaymentResponse> paymentResponse = paymentResponseService.getPaymentDetailsByOrderId(orderId);

        // Return response: 200 OK if found, 404 Not Found if not
        return paymentResponse
                .map(ResponseEntity::ok) // 200 OK with payment details if present
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 if no payment details found
    }


}
