package com.example.orderapp.service;

import com.example.orderapp.gateway.UsersGateway;
import com.example.orderapp.models.User;
import com.example.orderapp.models.UserInfoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UsersGateway usersGateway;

    @InjectMocks
    UserService userService;

    @Test
    void getTotalPages() {
        User user = mock(User.class);
        UserInfoResponse userInfoResponse = new UserInfoResponse(1,2,3,4,List.of(user));

        assertEquals(4,userService.getTotalPages(userInfoResponse));
    }
    @Test
    void getUserToCreateOrderFor() {
        when(usersGateway.getUsersInfo(1)).thenReturn(userApiPage1());

        assertEquals(userCreator(1),userService.getUserToCreateOrderFor("email1@email.com"));
    }
    @Test
    void getTotalPagesWhenThereIsNoResponse() {
        UserInfoResponse userInfoResponse = mock(UserInfoResponse.class);

        assertEquals(0,userService.getTotalPages(userInfoResponse));
    }

    @Test
    void findUserByEmailInFirstPage() {
        when(usersGateway.getUsersInfo(1)).thenReturn(userApiPage1());

        assertEquals(Optional.of(userCreator(1)),userService.findUserByEmail("email1@email.com",1));
    }

    @Test
    void findUserByEmailInSecondPage() {
        when(usersGateway.getUsersInfo(1)).thenReturn(userApiPage1());
        when(usersGateway.getUsersInfo(2)).thenReturn(userApiPage2());

        assertEquals(Optional.of(userCreator(4)),userService.findUserByEmail("email4@email.com",1));
    }

    @Test
    void noUserByEmail() {
        when(usersGateway.getUsersInfo(1)).thenReturn(userApiPage1());
        when(usersGateway.getUsersInfo(2)).thenReturn(userApiPage2());

        assertEquals(Optional.empty(), userService.findUserByEmail("email6@email.com",1));
    }

    private User userCreator(int userNumber) {
        return new User(1,"email" + String.valueOf(userNumber) +"@email.com", "user" + String.valueOf(userNumber), "lastname" + String.valueOf(userNumber), "avatar" + String.valueOf(userNumber));
    }

    private UserInfoResponse userApiPage1(){
        return new UserInfoResponse(1,2,2,2,List.of(userCreator(1), userCreator(2)));
    }

    private UserInfoResponse userApiPage2(){
        return new UserInfoResponse(2,2,2,2,List.of(userCreator(3), userCreator(4)));
    }
}