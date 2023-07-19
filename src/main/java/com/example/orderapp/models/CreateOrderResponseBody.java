package com.example.orderapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderResponseBody {
    @JsonProperty("orderId")
    int orderId;
}
