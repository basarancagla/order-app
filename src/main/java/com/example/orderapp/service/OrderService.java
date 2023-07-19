package com.example.orderapp.service;

import com.example.orderapp.models.OrdersResponseBody;
import com.example.orderapp.exception.ApiRequestException;
import com.example.orderapp.models.CreateOrderRequestBody;
import com.example.orderapp.entity.OrderEntity;
import com.example.orderapp.models.CreateOrderResponseBody;
import com.example.orderapp.models.User;
import com.example.orderapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;

    private final OrderRepository orderRepository;

    public CreateOrderResponseBody createOrder(final CreateOrderRequestBody createOrder) {
        final User userToCreateOrderFor = userService.getUserToCreateOrderFor(createOrder.getEmail());

        checkIfProductIsOrderedBeforeByUser(createOrder.getProductID(), createOrder.getEmail());

        OrderEntity order = OrderEntity.builder()
                .email(userToCreateOrderFor.getEmail())
                .firstName(userToCreateOrderFor.getFirst_name())
                .lastName(userToCreateOrderFor.getLast_name())
                .productID(createOrder.getProductID())
                .build();

        int orderId = orderRepository.save(order).getOrderID();

        return CreateOrderResponseBody.builder().orderId(orderId).build();
    }

    void checkIfProductIsOrderedBeforeByUser(final Integer productId, final String email) {
        orderRepository.findByProductIDAndEmail(productId, email)
                .ifPresent(order -> {
                    throw new ApiRequestException("User already ordered this product" );
                });
    }

    public List<OrdersResponseBody> findOrders() {
        return orderRepository.findAll()
                .stream()
                .map(customer->
                        new ModelMapper().map(customer, OrdersResponseBody.class))
                .collect(Collectors.toList());

    }
}