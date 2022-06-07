package com.revature.pms.services;

import com.revature.pms.dao.UserDAO;
import com.revature.pms.model.User;
import com.revature.pms.utilities.GenerateRandomId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    GenerateRandomId generateRandomId;

    @Autowired
    User user;

    @Override
    public boolean addUser(User user) {
        System.out.println("Adding user in service");
        userDAO.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(int user_id) {
        boolean result = true;
        try
        {
            userDAO.deleteById(user_id);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        userDAO.save(user);
        return true;
    }

    @Override
    public boolean isUserExists(int user_id) {
        return userDAO.existsById(user_id);
    }

    @Override
    public User getUserById(int user_id) {
        User user = new User();
        Optional<User> it = Optional.ofNullable(userDAO.getReferenceById(user_id));
        return it.get();
    }

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findByEmailAndPassword(String email, String password) {

        return userDAO.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public boolean saveEmailAndPassword(String email, String password){
//        do{
//            user.setUserId(generateRandomId.getRandomNumber());
//        }while (userDAO.existsById(user.getUserId()));

        user.setEmail(email);
        user.setPassword(password);
        userDAO.save(user);
        return user.getUserId() > 0;
    };
}
