package com.example.orderapp.controller;

import com.example.orderapp.entity.OrderEntity;
import com.example.orderapp.models.CreateOrderResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.orderapp.models.CreateOrderRequestBody;
import com.example.orderapp.service.OrderService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController  {

    private final OrderService orderService;

    @RequestMapping(value = "/create-order", method = RequestMethod.POST)
    public ResponseEntity<CreateOrderResponseBody> createOrder(@RequestBody CreateOrderRequestBody createOrderRequestBody) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequestBody));
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderEntity>> findOrders() {
        return ResponseEntity.ok(orderService.findOrders());
    }
}
