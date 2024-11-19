package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testCreateOrder() {
        Orders order = new Orders();
        when(orderService.createOrder(order)).thenReturn(order);

        ResponseEntity<Orders> response = orderController.createOrder(order);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllOrders() {
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        ResponseEntity<List<Orders>> response = orderController.getAllOrders();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetOrderByIdFound() {
        Orders order = new Orders();
        when(orderService.getOrderById(1L)).thenReturn(order);

        ResponseEntity<Orders> response = orderController.getOrderById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderService.getOrderById(1L)).thenReturn(null);

        ResponseEntity<Orders> response = orderController.getOrderById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
