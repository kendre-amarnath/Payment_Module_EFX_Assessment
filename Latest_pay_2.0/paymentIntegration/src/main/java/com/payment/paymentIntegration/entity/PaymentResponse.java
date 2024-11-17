package com.payment.paymentIntegration.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long orderId;
    private String paymentId;
    private String paymentLinkId;
    private String paymentStatus;
    private String paymentSignature;

}
