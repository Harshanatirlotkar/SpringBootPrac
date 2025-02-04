package com.harsh.myrest.service;

import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class DiscountService {

    public float calculateDiscount(float amount, String promocode) {
        if (promocode == null){
            return 0;
        }
        else if (promocode.equals("Thanksgiving")){
            return amount*0.1f;
        }
        else if (promocode.equals("xmas") && getCurrentYear().getValue() == 2025){
            return amount*0.25f;
        }
        return 0;
    }

    Year getCurrentYear(){
        return Year.now();
    }


}
