package com.revature.pms.controller;

import com.revature.pms.model.Item;
import com.revature.pms.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;
    boolean result;

    @GetMapping     //localhost:8084/item
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = new ArrayList<Item>();
        items = itemService.getItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("{pId}")     //localhost:8084/item/78
    public ResponseEntity<Item> getItemById(@PathVariable("pId") int itemId) {
        System.out.println("Fetching details about by item id  :" + itemId);
        //call the methods to fetch item details of this item id
        ResponseEntity responseEntity = null;
        Item item1 = new Item();
        if (itemService.isItemExists(itemId)) {
            item1 = itemService.getItemById(itemId);
            responseEntity = new ResponseEntity<Item>(item1, HttpStatus.OK);   //409
        } else {
            responseEntity = new ResponseEntity<Item>
                    (item1, HttpStatus.NO_CONTENT);        //204
        }
        return responseEntity;
    }

    @GetMapping("/searchItemByName/{Name}")     //localhost:8084/item/searchItemByName/Laptop
    public ResponseEntity<List<Item>> getItemByName(@PathVariable("Name") String itemName) {
        //call the methods to fetch item details of this itemName
        List<Item> items = itemService.getItemsByName(itemName);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);

    }

    //localhost:8084/item/findByItemPriceBetween/100/500
    @GetMapping("/findByItemPrice/{Price}")
    public ResponseEntity<List<Item>> findByItemPriceBetween(@PathVariable("Price") double Price) {
        //call the methods to fetch item details of this price
        List<Item> items = itemService.findByItemPrice(Price);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    //localhost:8084/item/findByItemPriceBetween/100/500
    @GetMapping("/findByItemPriceBetween/{lowerPrice}/{upperPrice}")
    public ResponseEntity<List<Item>> findByItemPriceBetween(@PathVariable("lowerPrice") double lowerPrice, @PathVariable("upperPrice") double upperPrice) {
        //call the methods to fetch item details of this price
        List<Item> items = itemService.findByItemPriceBetween(lowerPrice, upperPrice);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @PostMapping   //localhost:8084/item                   -HTTP method - POST
    public ResponseEntity<String> saveItem(@RequestBody Item item) {
        ResponseEntity responseEntity = null;
        if (itemService.isItemExists(item.getItemId())) {
            LOGGER.warn("Item with item id :" + item.getItemId() + "already exists");
            responseEntity = new ResponseEntity<String>
                    ("Cannot save because item with item id :" + item.getItemId() + " already exists", HttpStatus.CONFLICT);   //409
        } else {
            result = itemService.addItem(item);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully Saved your item:" + item.getItemId(), HttpStatus.OK);        //200
                LOGGER.info("Item with item id :" + item.getItemId() + " saved successfully");

            } else {
                responseEntity = new ResponseEntity<String>
                        ("Cannot save because item because price or qoh is negative", HttpStatus.NOT_ACCEPTABLE);        //406
                LOGGER.error("Item with item id :" + item.getItemId() + "cannot be saved because of negative qoh or price");

            }
        }
        return responseEntity;
    }

    @PutMapping   //localhost:8084/item                   -HTTP method - PUT
    public ResponseEntity<String> updateItem(@RequestBody Item item) {
        ResponseEntity responseEntity = null;
        System.out.println("Updating details  of :" + item);
        if (itemService.isItemExists(item.getItemId())) {
            boolean result = itemService.updateItem(item);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully updated your item:" + item.getItemId(), HttpStatus.OK); //send 200 with response
            } else {
                responseEntity = new ResponseEntity<String>
                        ("not  updated your item because price or qoh cannot be negative" + item.getItemId(), HttpStatus.NOT_MODIFIED); //send 200 with resp
            }
        } else {
            responseEntity = new ResponseEntity<String>
                    ("Cannot update item at ID: " + item.getItemId() + ", check that it exists ", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    /*
    Deleting a single item by id
     */
    @DeleteMapping("{pId}")     //localhost:8084/item/78                        -HTTP method - DELETE
    public ResponseEntity<String> deleteItem(@PathVariable("pId") int itemId) {
        System.out.println("Deleting details  by item id  :" + itemId);
        ResponseEntity<String> responseEntity;

        if (!(itemService.isItemExists(itemId))) responseEntity = new ResponseEntity<String>
                ("Cannot delete as id doesn't exist", HttpStatus.NO_CONTENT);
        else {
            if (itemService.deleteItem(itemId)) responseEntity = new ResponseEntity<String>
                    (itemId + " deleted", HttpStatus.OK);
            else responseEntity = new ResponseEntity<String>
                    (" Cannot delete, something went wrong", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
