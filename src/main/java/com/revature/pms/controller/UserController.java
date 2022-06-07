package com.revature.pms.controller;

import com.revature.pms.dao.CartDAO;
import com.revature.pms.dao.UserDAO;
import com.revature.pms.model.Cart;
import com.revature.pms.model.Item;
import com.revature.pms.model.User;
import com.revature.pms.model.User;
import com.revature.pms.services.CartServiceImpl;
import com.revature.pms.services.ItemServiceImpl;
import com.revature.pms.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDAO userDAO;
    @Autowired
    UserService userService;

    @Autowired
    CartServiceImpl cartService;

    boolean result;

    @Autowired
    ItemServiceImpl itemService;

    @GetMapping     //localhost:8084/user
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<User>();
        users = userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("{pId}")     //localhost:8084/user/78
    public ResponseEntity<User> getUserById(@PathVariable("pId") int userId) {
        System.out.println("Fetching details about by user id  :" + userId);
        //call the methods to fetch user details of this user id
        ResponseEntity responseEntity = null;
        User user1 = new User();
        if (userService.isUserExists(userId)) {
            user1 = userService.getUserById(userId);
            responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);   //409
        } else {
            responseEntity = new ResponseEntity<User>
                    (user1, HttpStatus.NO_CONTENT);        //204
        }
        return responseEntity;
    }

    @GetMapping("/searchUserByName/{Name}")     //localhost:8084/user/searchUserByName/Laptop
    public ResponseEntity<List<User>> getUserByName(@PathVariable("Name") String userName) {
        //call the methods to fetch user details of this username
        return new ResponseEntity<List<User>>(userService.findByEmail(userName), HttpStatus.OK);
    }

    //localhost:8084/user/findByUserPriceBetween/100/500
    @GetMapping("/findByEmailAndPassword/{email}/{password}")
    public ResponseEntity<List<User>> findByUserPrice(@PathVariable("email") String email, @PathVariable("password") String password) {
        //call the methods to fetch user details of this price
        List<User> users = userService.findByEmailAndPassword(email, password);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping   //localhost:8084/user                   -HTTP method - POST
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity = null;
//        if (userService.isUserExists(user.getUserId())) {
//            LOGGER.warn("User with user id :" + user.getUserId() + "already exists");
//            responseEntity = new ResponseEntity<String>
//                    ("Cannot save because user with user id :" + user.getUserId() + " already exists", HttpStatus.CONFLICT);   //409
//        } else {
//            result = userService.addUser(user);
//            if (result) {
//                responseEntity = new ResponseEntity<String>
//                        ("Successfully Saved your user:" + user.getUserId(), HttpStatus.OK);        //200
//                LOGGER.info("User with user id :" + user.getUserId() + " saved successfully");
//            }
//        }

        userDAO.save(user);
        responseEntity = new ResponseEntity<String>
                ("Successfully Saved your user:" + user.getUserId(), HttpStatus.OK);        //200
        LOGGER.info("User with user id :" + user.getUserId() + " saved successfully");
        return responseEntity;
    }

    @PostMapping("/addItemToCart")//localhost:8084/user                   -HTTP method - POST
    public ResponseEntity<String> addItemToCart(@RequestBody Cart cart) {
        ResponseEntity responseEntity = null;

        result = cartService.addCart(cart);
        if (result) {
            responseEntity = new ResponseEntity<String>
                    ("Successfully Saved your user:" + cart.getId(), HttpStatus.OK);        //200
            LOGGER.info("User with user id :" + cart.getId() + " saved successfully");
        }
        return responseEntity;
    }

    @PostMapping("insertToUsersEmailAndPassword")   //localhost:8084/user                   -HTTP method - POST
    public ResponseEntity<String> insertToUsersEmailAndPassword(@RequestBody User user) {
        ResponseEntity responseEntity = null;
        result = userService.saveEmailAndPassword(user.getEmail(), user.getPassword());
        if (result) {
            responseEntity = new ResponseEntity<String>
                    ("Successfully Saved your user:" + user.getUserId(), HttpStatus.OK);        //200
            LOGGER.info("User with user email :" + user.getEmail() + " saved successfully");
        }
        return responseEntity;
    }

    @PutMapping   //localhost:8084/user                   -HTTP method - PUT
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        ResponseEntity responseEntity = null;
        System.out.println("Updating details  of :" + user);
        if (userService.isUserExists(user.getUserId())) {
            boolean result = userService.updateUser(user);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully updated your user:" + user.getUserId(), HttpStatus.OK); //send 200 with response
            } else {
                responseEntity = new ResponseEntity<String>
                        ("not  updated your user because price or qoh cannot be negative" + user.getUserId(), HttpStatus.NOT_MODIFIED); //send 200 with resp
            }
        } else {
            responseEntity = new ResponseEntity<String>
                    ("Cannot update user at ID: " + user.getUserId() + ", check that it exists ", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    /*
    Deleting a single user by id
     */
    @DeleteMapping("{pId}")     //localhost:8084/user/78                        -HTTP method - DELETE
    public ResponseEntity<String> deleteUser(@PathVariable("pId") int userId) {
        System.out.println("Deleting details  by user id  :" + userId);
        ResponseEntity<String> responseEntity;

        if (!(userService.isUserExists(userId))) responseEntity = new ResponseEntity<String>
                ("Cannot delete as id doesn't exist", HttpStatus.NO_CONTENT);
        else {
            if (userService.deleteUser(userId)) responseEntity = new ResponseEntity<String>
                    (userId + " deleted", HttpStatus.OK);
            else responseEntity = new ResponseEntity<String>
                    (" Cannot delete, something went wrong", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
