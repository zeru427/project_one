package com.revature.pms.services;

import com.revature.pms.model.User;
import com.revature.pms.model.User;

import java.util.List;

public interface UserService {
    //    void register(User user);
//    Optional<User> login(User user);
//    List<User> getUsers();
    public boolean addUser(User user);

    public boolean deleteUser(int user_id);

    public boolean updateUser(User user);

    public boolean isUserExists(int user_id);

    User getUserById(int user_id);

    List<User> getUsers();


    List<User> findByEmailAndPassword(String email, String password);

    List<User> findByEmail(String email);

    boolean saveEmailAndPassword(String email, String password);
}
