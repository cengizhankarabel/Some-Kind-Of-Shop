package com.revature.service;


import com.revature.model.Item;
import com.revature.model.User;

import java.util.List;

public interface ItemService {

    void setUser(User user);

    void addItem(String itemName, double itemPrice, boolean isAvailable);

    void editItem(int id, double newItemPrice);

    void editItem(int id, boolean isAvailable);

    void deleteItem(int id);

    List<Item> viewAvailableItems();

    List<Item> getOwnItems();

    List<Item> viewAllItems();

}
