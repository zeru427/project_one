package com.revature.pms.services;

import com.revature.pms.dao.ItemDAO;
import com.revature.pms.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDAO itemDAO;

    @Override
    public boolean addItem(Item item) {
        System.out.println("Adding item in service");
         itemDAO.save(item);
        return true;
    }

    @Override
    public boolean deleteItem(int item_id) {
        boolean result = true;
        try
        {
            itemDAO.deleteById(item_id);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateItem(Item item) {
        itemDAO.save(item);
        return true;
    }

    @Override
    public boolean isItemExists(int item_id) {
        return itemDAO.existsById(item_id);
    }

    @Override
    public Item getItemById(int item_id) {
        Item item = new Item();
        Optional<Item> it = Optional.ofNullable(itemDAO.getReferenceById(item_id));
        return it.get();
    }

    @Override
    public List<Item> getItems() {
        return itemDAO.findAll();
    }

    @Override
    public List<Item> findByItemPriceBetween(double minimumPrice, double maximumPrice) {
        return itemDAO.findByItemPriceBetween(minimumPrice,maximumPrice);
    }

    @Override
    public List<Item> findByItemPrice(double price) {
        return itemDAO.findByItemPrice(price);
    }

    @Override
    public List<Item> getItemsByName(String name) {
        return itemDAO.findByItemName(name);
    }

    @Override
    public List<Item> getItemByGreaterQoh(int greaterQoh) {
        return null;
    }
}
