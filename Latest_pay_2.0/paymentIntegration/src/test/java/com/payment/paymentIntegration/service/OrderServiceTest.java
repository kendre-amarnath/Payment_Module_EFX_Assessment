package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.entity.Status;
import com.payment.paymentIntegration.exception.OrderNotFoundException;
import com.payment.paymentIntegration.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        Orders order = new Orders();
        when(orderRepository.save(order)).thenReturn(order);

        Orders result = orderService.createOrder(order);
        assertNotNull(result);
        verify(orderRepository).save(order);
    }

    @Test
    void testGetOrderByIdFound() {
        Orders order = new Orders();
        order.setOrderId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Orders result = orderService.getOrderById(1L);
        assertEquals(1L, result.getOrderId());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1L));
    }

    @Test
    void testUpdateOrderStatus() {
        Orders order = new Orders();
        order.setOrderId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Orders updatedOrder = new Orders();
        updatedOrder.setOrderId(1L);
        updatedOrder.setStatus(Status.SUCCESS);
        when(orderRepository.save(order)).thenReturn(updatedOrder);
        Orders result = orderService.updateOrderStatus(1L, Status.SUCCESS);
        assertEquals(Status.SUCCESS, result.getStatus());
    }


    @Test
    void testGetAllOrders() {
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Orders> ordersList = orderService.getAllOrders();
        assertEquals(2, ordersList.size());
    }
}
