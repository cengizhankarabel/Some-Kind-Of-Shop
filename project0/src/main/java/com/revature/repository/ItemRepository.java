package com.revature.repository;


import com.revature.model.Customer;
import com.revature.model.Item;
import com.revature.model.StatusFilter;

import java.util.ArrayList;
import java.util.List;

public interface ItemRepository {  // Dependency Object

    // data accessing methods ( dao ) on any data source ( database )
    void addItem(Item item);

    void updateItem(int id, double price);

    void updateItem(int id, boolean isAvailable);

    List<Item> viewAvailableItems();


    List<Item> findOwnItems(int userId);


    void removeItem(int id);


    List<Item> viewAllItems();
}
