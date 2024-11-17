package com.payment.paymentIntegration.controller;


import com.payment.paymentIntegration.dto.StatusRequestDto;
import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.entity.Status;
import com.payment.paymentIntegration.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:4200/")
public class OrderController {

    @Autowired
    private OrderService ordersService;

    // to create a new order
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // To get all list of the orders
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> ordersList = ordersService.getAllOrders();
        return ResponseEntity.ok(ordersList);
    }

    // TO fetch order from given id
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Optional<Orders> order = ordersService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // This code is use to change status
    @PutMapping("/status")
    public ResponseEntity<Orders> updateOrderStatus(@RequestBody StatusRequestDto statusRequestDto) {
        Orders updatedOrder = ordersService.updateOrderStatus(statusRequestDto.getOrderId(),statusRequestDto.getStatus());

        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}




