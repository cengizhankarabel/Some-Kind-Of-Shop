package com.revature.service;

import com.revature.model.Payment;
import com.revature.model.User;

import java.util.List;

public interface PaymentService {

    void setUser(User user);

    void payRemainingPayment(int payId);

    List<Payment> viewAllRemainingPayment();

    List<Payment> viewMyRemainingPayment();

    List<Payment> viewMyCompletedPayment();

    List<Payment> viewAllCompletedPayment();
}
