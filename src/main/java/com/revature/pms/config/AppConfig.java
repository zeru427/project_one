package com.revature.pms.config;

import com.revature.pms.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //   @Scope(value = "prototype")
    @Bean
    public User user() {
        return new User();
    }
    @Bean
    public Item item() {
        return new Item();
    }
    @Bean
    public Cart cart() {
        return new Cart();
    }
}
