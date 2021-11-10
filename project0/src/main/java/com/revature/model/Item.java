package com.revature.model;

import java.util.List;
import java.util.Objects;


// Object Model
public class Item {

    private int itemId;
    private String itemName;
    private double itemPrice;
    private boolean isAvailable;
    private int userId;

    public Item() {

    }

    public Item(String itemName, double itemPrice, boolean isAvailable) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.isAvailable = isAvailable;
    }

    private User user;

    // accessor methods
    public User getUser() {
        return user;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUser(User user){
        this.user = user;

    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", isAvailable=" + isAvailable +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && Double.compare(item.itemPrice, itemPrice) == 0 && isAvailable == item.isAvailable && Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemPrice, isAvailable);
    }
}
