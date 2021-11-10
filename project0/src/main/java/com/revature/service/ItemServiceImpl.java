package com.revature.service;



import com.revature.model.Item;
import com.revature.model.User;
import com.revature.repository.ItemRepository;
import org.apache.log4j.Logger;

import java.util.List;

/*
        service:

        process data based business rules

 */



public class ItemServiceImpl implements ItemService{   //Dependent object

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    private ItemRepository itemRepository;
    private User user;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void addItem(String itemName, double itemPrice, boolean isAvailable) {

        logger.info("Item sent to Database...");

        Item newItem=new Item(itemName, itemPrice, isAvailable);
        newItem.setUser(user);
        itemRepository.addItem(newItem);
    }

    @Override
    public void editItem(int id, double newItemPrice) {

        logger.info("Item edited...");

        itemRepository.updateItem(id, newItemPrice);
    }

    @Override
    public void editItem(int id, boolean isAvailable) {

        logger.info("Item edited...");

        itemRepository.updateItem(id, isAvailable);
    }

    @Override
    public void deleteItem(int id) {

        logger.info("Item deleted...");

        itemRepository.removeItem(id);
    }

    @Override
    public List<Item> viewAvailableItems() {
        return itemRepository.viewAvailableItems();
    }

    @Override
    public List<Item> getOwnItems() {
        return itemRepository.findOwnItems(user.getUserId());
    }

    @Override
    public List<Item> viewAllItems() {
        return itemRepository.viewAllItems();
    }
}
