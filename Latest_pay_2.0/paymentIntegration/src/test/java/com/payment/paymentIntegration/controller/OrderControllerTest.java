package com.payment.paymentIntegration.controller;

import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.dto.StatusRequestDto;
import com.payment.paymentIntegration.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.payment.paymentIntegration.entity.Status.COMPLETED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    public OrderControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Orders mockOrder = new Orders();
        when(orderService.createOrder(any(Orders.class))).thenReturn(mockOrder);
        ResponseEntity<Orders> response = orderController.createOrder(mockOrder);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).createOrder(mockOrder);
    }

    @Test
    void testGetAllOrders() {
        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
        when(orderService.getAllOrders()).thenReturn(mockOrders);

        ResponseEntity<List<Orders>> response = orderController.getAllOrders();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrders, response.getBody());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testGetOrderById() {
        Orders mockOrder = new Orders(); // Add fields
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<Orders> response = orderController.getOrderById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void testUpdateOrderStatus() {
        Orders mockOrder = new Orders();
        StatusRequestDto statusRequest = new StatusRequestDto(1L, COMPLETED);
        when(orderService.updateOrderStatus(1L, COMPLETED)).thenReturn(mockOrder);

        ResponseEntity<Orders> response = orderController.updateOrderStatus(statusRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).updateOrderStatus(1L, COMPLETED);
    }
}
