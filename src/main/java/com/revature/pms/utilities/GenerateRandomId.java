package com.revature.pms.utilities;

import org.springframework.stereotype.Component;

@Component
public class GenerateRandomId {

    public int getRandomNumber(){
        return  (int)(2147483647*Math.random());
    }
}
