package com.revature.pms.dao;

import com.revature.pms.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item,Integer> {
    //declare the methods
    //select * from item where itemName = ?
    public List<Item> findByItemName(String itemName);
    public List<Item> findByItemPrice(double price);
//
//    public List<Item> findByQoh(int qoh);
//
    public List<Item> findByItemPriceBetween(double minimumItemPrice,double maximumItemPrice);
//
//    //? select * from item where qoh < ?
//    public List<Item>findByQohLessThan(int qoh);
//
//    //select * from item where itemName = ?
//    @Query("select p from Item p where itemName = ?1")
//    public List<Item>  getItemByName(String itemName);
//
//    @Query("SELECT c FROM Item c WHERE c.itemName = ?1 AND c.itemPrice = ?2")
//    public List<Item> findItemByNameAndPrice(String itemName, int price);

}
