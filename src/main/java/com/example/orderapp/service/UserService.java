package com.example.orderapp.service;

import com.example.orderapp.exception.ApiRequestException;
import com.example.orderapp.gateway.UsersGateway;
import com.example.orderapp.models.UserInfoResponse;
import com.example.orderapp.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private static final int FIRST_PAGE = 1;

    private final UsersGateway usersGateway;

    static Integer getTotalPages(final UserInfoResponse response) {
        return Optional.ofNullable(response)
                .map(UserInfoResponse::getTotal_pages)
                .orElse(0);
    }

    public User getUserToCreateOrderFor(final String email) {
        return findUserByEmail(email, FIRST_PAGE)
                .orElseThrow(() -> new ApiRequestException("User not found" ));
    }

    Optional<User> findUserByEmail(final String email, final int pageNumber) {
        UserInfoResponse usersResponse = usersGateway.getUsersInfo(pageNumber);

        Optional<User> userToCreateOrderFor = usersResponse.getData().stream()
                .filter(user -> email.equalsIgnoreCase(user.getEmail()))
                .findAny();

        if (userToCreateOrderFor.isEmpty() && getTotalPages(usersResponse) > pageNumber) {
            return findUserByEmail(email, pageNumber + 1);
        }
        return userToCreateOrderFor;
    }
}
