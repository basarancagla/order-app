package com.example.orderapp.gateway;

import com.example.orderapp.models.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation. GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-tax-info", url = "https://reqres.in/api")
public interface UsersGateway{
    @GetMapping(value = "/users?page={page}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    UserInfoResponse getUsersInfo(@RequestParam("page") Integer pageNumber);

}