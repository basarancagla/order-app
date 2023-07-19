package com.example.orderapp.service;

import com.example.orderapp.entity.OrderEntity;
import com.example.orderapp.exception.ApiRequestException;
import com.example.orderapp.models.CreateOrderRequestBody;
import com.example.orderapp.models.CreateOrderResponseBody;
import com.example.orderapp.repository.OrderRepository;
import com.example.orderapp.models.User;
import static org.mockito.ArgumentMatchers.any;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    OrderService orderService;

    private final String email = "caglabasaran93@gmail.com";
    private final int productId = 1;

    @Test
    void createOrder() {
        User user = new User(1,"email1@email.com", "user1", "lastname1", "avatar1");
        final CreateOrderRequestBody createOrder = new CreateOrderRequestBody(productId, email);
        final OrderEntity orderEntity = new OrderEntity(1,email,"cagla","basaran",2);

        when(userService.getUserToCreateOrderFor(anyString())).thenReturn(user);
        when(orderRepository.findByProductIDAndEmail(anyInt(), anyString())).thenReturn(Optional.empty());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        CreateOrderResponseBody order = orderService.createOrder(createOrder);

        assertEquals(1,order.getOrderId());
    }

    @Test
    void checkIfProductIsOrderedBeforeByUser()  {
        OrderEntity orderEntity = mock(OrderEntity.class);

        when(orderRepository.findByProductIDAndEmail(anyInt(), anyString())).thenReturn(Optional.of(orderEntity));

        assertThrows(ApiRequestException.class, () -> orderService.checkIfProductIsOrderedBeforeByUser(productId, email));
    }

    @Test
    void findOrders() {
        OrderEntity orderEntity1 = mock(OrderEntity.class);
        OrderEntity orderEntity2 = mock(OrderEntity.class);

        when(orderRepository.findAll()).thenReturn(List.of(orderEntity1, orderEntity2));
        orderService.findOrders();
        assertEquals(2,orderService.findOrders().size());
    }
}