package com.revature.service;

import com.revature.model.User;

import java.util.List;

public interface UserService {

    void registerCustomerAccount(User user);

    void createEmployeeAccount(User user);

    User loginUser(String email, String password);

    void updateUser(); // TODO: 11/5/2021  

    void removeEmployeeAcc(int userId);

    List<User> employeeAccList();

}
