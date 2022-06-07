package com.revature.pms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId;
        private String email;
        private String password;
//        @OneToMany
//        @JoinColumn(name="user_id")
//        private List<Cart> CartList = new ArrayList<Cart>();
//        @OneToMany
//        @JoinColumn(name="user_id")
//        private List<Order> OrderList = new ArrayList<Order>();
}
