package com.payment.paymentIntegration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaymentResponseDto {
    private String orderId;
    private String paymentId;
    private String paymentLinkId;
    private String paymentStatus;
    private String paymentSignature;

}
