package com.revature.pms.dao;

import com.revature.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    List<User> findByEmailAndPassword(String email, String password);
//@Transactional
//    @Modifying
    @Query(value = "insert into User(email,password) values(?1,?2)", nativeQuery = true)
    void insertToUser(String email, String password);
//    //    @Query( "insert into User(userId,email,password) values(?1,?2,?3)")

        @Query("SELECT c FROM User c WHERE c.email = ?1")
    public List<User> findItemByNameAndPrice(String itemName);

    List<User> findByEmail(String email);

}