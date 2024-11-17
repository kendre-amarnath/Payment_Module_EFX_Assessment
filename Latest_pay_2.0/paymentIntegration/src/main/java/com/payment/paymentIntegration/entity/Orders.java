package com.payment.paymentIntegration.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.payment.paymentIntegration.entity.Status.PENDING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long orderId;
    private String email;
    private String contact;
    private  int amount;
    private String name;
    private String address;
    private Status status = PENDING;

}
