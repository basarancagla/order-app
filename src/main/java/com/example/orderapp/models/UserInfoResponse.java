package com.example.orderapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
}
