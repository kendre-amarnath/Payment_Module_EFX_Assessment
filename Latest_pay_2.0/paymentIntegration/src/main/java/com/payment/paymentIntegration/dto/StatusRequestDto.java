package com.payment.paymentIntegration.dto;

import com.payment.paymentIntegration.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequestDto {

    private Long orderId;
    private Status status;
}
