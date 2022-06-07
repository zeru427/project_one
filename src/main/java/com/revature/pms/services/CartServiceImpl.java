package com.revature.pms.services;

import com.revature.pms.dao.CartDAO;
import com.revature.pms.dao.OrderDAO;
import com.revature.pms.model.Cart;
import com.revature.pms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDAO cartDAO;

    @Autowired
    OrderDAO orderDAO;

    @Override
    public boolean addCart(Cart cart) {
        System.out.println("Adding cart in service");

        boolean result = true;
        try
        {
//            cartDAO.save(cart);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;

    }

    @Override
    public boolean addOrder(Order order) {
        System.out.println("Adding cart in service");

        boolean result = true;
        try
        {
//            cartDAO.save(order);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;

    }
}
