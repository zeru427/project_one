package com.revature.pms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    private int id;
    private int item_id;
    private int user_id;
}
