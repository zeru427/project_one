package com.revature.pms.dao;

import com.revature.pms.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDAO extends JpaRepository<Item,Integer> {

}
