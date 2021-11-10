package com.revature.model;

public class Offer {

    private int id;
    private double offerPrice;
    private int itemId;
    private int userId;
    private StatusFilter offerStatus;

    private User user;
    private Item item;

    public Offer() {
    }

    public Offer(int id, double offerPrice, int itemId, int userId, StatusFilter offerStatus) {
        this.id = id;
        this.offerPrice = offerPrice;
        this.itemId = itemId;
        this.userId = userId;
        this.offerStatus = offerStatus;
    }

    // accessor methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public StatusFilter getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(StatusFilter offerStatus) {
        this.offerStatus = offerStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", offerPrice=" + offerPrice +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
