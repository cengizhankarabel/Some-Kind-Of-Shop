package com.revature.repository;

import com.revature.model.Offer;
import com.revature.model.Payment;

import java.util.List;

public interface PaymentRepository {

    void payRemainingPayment(int payId);

    List<Payment> viewAllRemainingPayment();

    List<Payment> viewMyRemainingPayment(int userId);

    List<Payment> viewAllCompletedPayment();

    List<Payment> viewMyCompletedPayment(int userId);
}
