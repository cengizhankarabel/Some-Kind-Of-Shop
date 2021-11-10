package com.revature.service;

import com.revature.exceptions.InvalidInputException;
import com.revature.model.User;
import com.revature.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import  static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    User user=new User();

    @BeforeEach
    public void setup(){
        userRepository= Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void registerCustomerAccountTest(){
        User user =new User();
        user.setLoginPassword("12345");
        userService.registerCustomerAccount(user);
        Mockito.verify(userRepository, Mockito.times(1)).registerCustomerAccount(new User());
    }

    @Test
    @DisplayName("Password cannot be empty")
    public User loginUserEmptyPasswordTest(){

        InvalidInputException exception=assertThrows(InvalidInputException.class,()->{
            userService.loginUser("foo@foo","");
        });
        assertEquals("password cannot be empty",exception.getMessage());
        return user;
    }

    @Test
    @DisplayName("username cannot be empty")
    public User loginUserEmptyUsernameTest(){
        InvalidInputException exception=assertThrows(InvalidInputException.class,()->{
            userService.loginUser("","bar");
        });
        assertEquals("username cannot be empty",exception.getMessage());
        return user;
    }




}
