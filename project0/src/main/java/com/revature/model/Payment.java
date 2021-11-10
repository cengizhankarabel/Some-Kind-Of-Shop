package com.revature.model;

public class Payment {
    private int id;
    private int offerId;
    private int itemId;
    private int userId;
    private double paymentAmount;
    private PaymentStatus paymentStatus;

    public Payment() {
    }

    public Payment(int id, int offerId, int itemId, int userId,
                   double paymentAmount, PaymentStatus paymentStatus) {

        this.id = id;
        this.offerId = offerId;
        this.itemId = itemId;
        this.userId = userId;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", offerId=" + offerId +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", paymentAmount=" + paymentAmount +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
