package com.revature.repository;

import com.revature.model.User;

import java.util.List;

public interface UserRepository {

    void registerCustomerAccount(User user);

    void createEmployeeAccount(User user);

    User findByEmail(String email);

    void removeEmployeeAcc(int userId);

    List<User> employeeAccList();



}
