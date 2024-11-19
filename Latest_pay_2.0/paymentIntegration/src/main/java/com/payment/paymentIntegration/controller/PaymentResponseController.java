// PaymentResponseController.java
package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.entity.PaymentResponse;
import com.payment.paymentIntegration.service.PaymentResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payments/response")
@CrossOrigin("http://localhost:4200/")
public class PaymentResponseController {

    @Autowired
    private PaymentResponseService paymentResponseService;

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

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPaymentDetails() {
        List<PaymentResponse> paymentResponses = paymentResponseService.getAllPaymentDetails();
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponses);
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId) {
        PaymentResponse paymentResponse = paymentResponseService.getPaymentDetailsByOrderId(orderId);
        if(paymentResponse == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
        }
    }


    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByPaymentId(@PathVariable("orderId") Long orderId) {
        System.out.println("Received request for payment ID: " + orderId);

        PaymentResponse paymentResponse = paymentResponseService.getPaymentDetailsByOrderId(orderId);

        if(paymentResponse == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        else{
            return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
        }
    }


}
