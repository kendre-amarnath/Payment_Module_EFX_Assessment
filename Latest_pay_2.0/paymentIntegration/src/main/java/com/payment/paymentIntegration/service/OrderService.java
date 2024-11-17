package com.payment.paymentIntegration.service;


import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.entity.Status;
import com.payment.paymentIntegration.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository ordersRepository;

    // Method to save a new order
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // Method to get all orders
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Method to get an order by ID
    public Optional<Orders> getOrderById(Long orderId)
    {

        return ordersRepository.findById(orderId);
    }


    public Orders updateOrderStatus(Long orderId, Status newStatus) {

        Optional<Orders> existingOrderOpt = ordersRepository.findById(orderId);

        if (existingOrderOpt.isPresent())
        {
            Orders existingOrder = existingOrderOpt.get();
            existingOrder.setStatus(newStatus);
            return ordersRepository.save(existingOrder);
        }
        else {
            return null;
        }
    }
}




