package com.example.orderapp.models;

import lombok.Data;
@Data
public class OrdersResponseBody {
    private Integer orderID;
    private String email;
    private String firstName;
    private String lastName;
    private Integer productID;
}
