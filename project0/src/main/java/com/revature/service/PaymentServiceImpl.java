package com.revature.service;

import com.revature.model.Payment;
import com.revature.model.User;
import com.revature.repository.ItemRepository;
import com.revature.repository.PaymentRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class PaymentServiceImpl implements PaymentService{

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    private PaymentRepository paymentRepository;
    private User user;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void payRemainingPayment(int payId) {
        logger.info("Payment initiated...");
        paymentRepository.payRemainingPayment(payId);
    }

    @Override
    public List<Payment> viewAllRemainingPayment() {
        return paymentRepository.viewAllRemainingPayment();
    }

    @Override
    public List<Payment> viewMyRemainingPayment() {
        return paymentRepository.viewMyRemainingPayment(user.getUserId());
    }

    @Override
    public List<Payment> viewMyCompletedPayment() {
        return paymentRepository.viewMyCompletedPayment(user.getUserId());
    }

    @Override
    public List<Payment> viewAllCompletedPayment() {
        return paymentRepository.viewAllCompletedPayment();
    }
}
