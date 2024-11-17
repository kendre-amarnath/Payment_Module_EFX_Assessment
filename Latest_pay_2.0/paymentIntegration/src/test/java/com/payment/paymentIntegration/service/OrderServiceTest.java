package com.payment.paymentIntegration.service;

import com.payment.paymentIntegration.entity.Orders;
import com.payment.paymentIntegration.entity.Status;
import com.payment.paymentIntegration.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Orders mockOrder = new Orders(); // Set fields as necessary
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Orders createdOrder = orderService.createOrder(mockOrder);

        assertEquals(mockOrder, createdOrder);
        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    void testGetAllOrders() {
        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Orders> orders = orderService.getAllOrders();

        assertEquals(mockOrders, orders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById() {
        Orders mockOrder = new Orders(); // Set fields as necessary
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        Optional<Orders> order = orderService.getOrderById(1L);

        assertEquals(Optional.of(mockOrder), order);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateOrderStatus() {
        Orders mockOrder = new Orders(); // Set fields as necessary
        mockOrder.setStatus(Status.PENDING);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Orders updatedOrder = orderService.updateOrderStatus(1L, Status.COMPLETED);

        assertEquals(Status.COMPLETED, updatedOrder.getStatus());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(mockOrder);
    }
}
