package com.example.orderapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
