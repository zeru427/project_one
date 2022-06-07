package com.revature.pms.services;

import com.revature.pms.model.Cart;
import com.revature.pms.model.Order;

import java.util.List;

public interface CartService {
    public boolean addCart(Cart cart);

    boolean addOrder(Order order);
}
