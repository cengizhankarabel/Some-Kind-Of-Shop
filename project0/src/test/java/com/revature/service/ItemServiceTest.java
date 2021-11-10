package com.revature.service;

import com.revature.model.Item;
import com.revature.model.User;
import com.revature.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ItemServiceTest {

    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    public void setup(){
        itemRepository= Mockito.mock(ItemRepository.class);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    public void addItemTest(){
        itemService.addItem("item-1",100.00,true);
        Mockito.verify(itemRepository, Mockito.times(1)).addItem(new Item("item-1",100.00,true));
    }

    @Test
    public void viewAvailableItems(){
        User user =new User();
        user.setUserId(1);
        Mockito.when(itemRepository.viewAvailableItems()).thenReturn(
                List.of(
                        new Item(),
                        new Item()
                )
        );
        itemService.setUser(user);
        List<Item> items = itemService.viewAvailableItems();
        Assertions.assertEquals(2, items.size());
    }

    @Test
    public void getOwnItemsTest(){
        User user =new User();
        user.setUserId(1);
        Mockito.when(itemRepository.findOwnItems(user.getUserId())).thenReturn(
                List.of(
                        new Item(),
                        new Item()
                )
        );
        itemService.setUser(user);
        List<Item> items = itemService.getOwnItems();
        Assertions.assertEquals(2, items.size());
    }

    @Test
    public void viewAllItemsTest(){

        User user = new User();
        user.setUserId(1);
        Mockito.when(itemRepository.viewAllItems()).thenReturn(
                List.of(
                        new Item(),
                        new Item()
                )
        );
        itemService.setUser(user);
        List<Item> items = itemService.viewAllItems();
        Assertions.assertEquals(2, items.size());
    }






}

