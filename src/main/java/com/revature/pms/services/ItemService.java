package com.revature.pms.services;

import com.revature.pms.model.Item;

import java.util.List;

public interface ItemService {
    public boolean addItem(Item item);

    public boolean deleteItem(int item_id);

    public boolean updateItem(Item item);

    public boolean isItemExists(int item_id);

    Item getItemById(int item_id);

    List<Item> getItems();

    List<Item> findByItemPriceBetween(double minimumPrice, double maximumPrice);
    List<Item> findByItemPrice(double price);
    List<Item> getItemsByName(String name);
    List<Item> getItemByGreaterQoh(int greaterQoh);

}
