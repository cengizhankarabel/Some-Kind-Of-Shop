package com.revature.service;



import com.revature.model.Payment;
import com.revature.model.User;
import com.revature.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    Payment payment=new Payment();

    User user=new User();

    @BeforeEach
    public void setup(){
        paymentRepository= Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    public void viewAllCompletedPaymentTest(){
        user.setUserId(1);
        Mockito.when(paymentRepository.viewAllCompletedPayment()).thenReturn(
                List.of(
                        new Payment(),
                        new Payment()
                )
        );
        paymentService.setUser(user);
        List<Payment> payments = paymentService.viewAllCompletedPayment();
        Assertions.assertEquals(2, payments.size());
    }

    @Test
    public void viewMyCompletedPaymentTest(){
        user.setUserId(1);
        Mockito.when(paymentRepository.viewMyCompletedPayment(user.getUserId())).thenReturn(
                List.of(
                        new Payment(),
                        new Payment()
                )
        );
        paymentService.setUser(user);
        List<Payment> payments = paymentService.viewMyCompletedPayment();
        Assertions.assertEquals(2, payments.size());
    }

    @Test
    public void viewMyRemainingPaymentTest(){
        user.setUserId(1);
        Mockito.when(paymentRepository.viewMyRemainingPayment(user.getUserId())).thenReturn(
                List.of(
                        new Payment(),
                        new Payment()
                )
        );
        paymentService.setUser(user);
        List<Payment> payments = paymentService.viewMyRemainingPayment();
        Assertions.assertEquals(2, payments.size());
    }

    @Test
    public void viewAllRemainingPaymentTest(){
        user.setUserId(1);
        Mockito.when(paymentRepository.viewAllRemainingPayment()).thenReturn(
                List.of(
                        new Payment(),
                        new Payment()
                )
        );
        paymentService.setUser(user);
        List<Payment> payments = paymentService.viewAllRemainingPayment();
        Assertions.assertEquals(2, payments.size());
    }

    @Test
    public void PaymentServiceImplTest(){

        user.setLoginPassword("12345");
        paymentService.payRemainingPayment(payment.getId());
        Mockito.verify(paymentRepository, Mockito.times(1)).payRemainingPayment(payment.getId());
    }

}
