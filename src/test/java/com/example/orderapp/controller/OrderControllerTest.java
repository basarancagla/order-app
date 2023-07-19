package com.example.orderapp.controller;

import com.example.orderapp.models.CreateOrderRequestBody;
import com.example.orderapp.models.OrdersResponseBody;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class OrderControllerTest {
    private static final String CREATE_URL = "/create-order";
    private static final String GET_URL = "/orders";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void createOrderTest() {
        var typeReference = new ParameterizedTypeReference<String>() {};
        var response = postRequest(requestBody())
                .expectStatus()
                .isOk()
                .expectBody(typeReference)
                .returnResult()
                .getResponseBody();

        assertEquals(true, response != null);
        assertEquals("{\"orderId\":1}", response);
    }

    @Test
    @Order(2)
    void findOrdersTest()  {
        var typeReference = new ParameterizedTypeReference<List<OrdersResponseBody>>() {};
        var response = getRequest()
                .expectStatus()
                .isOk()
                .expectBody(typeReference)
                .returnResult()
                .getResponseBody();

        assertEquals(1, response.size());
    }

    private ResponseSpec getRequest() {
        return webTestClient.get()
                .uri(GET_URL)
                .exchange();
    }

    private ResponseSpec postRequest(CreateOrderRequestBody postRequestBody) {
        return webTestClient.post()
                .uri(CREATE_URL)
                .body(BodyInserters.fromValue(postRequestBody))
                .exchange();
    }

    public static CreateOrderRequestBody requestBody() {
        return new CreateOrderRequestBody(123, "charles.morris@reqres.in");
    }
}