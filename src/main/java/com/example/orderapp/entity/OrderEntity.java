package com.example.orderapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "Orders")
@NoArgsConstructor(force = true)
public class OrderEntity {
    @Id
    @GeneratedValue
    private Integer orderID;

    private String email;

    private String firstName;

    private String lastName;

    private Integer productID;
}
