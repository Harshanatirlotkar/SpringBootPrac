package com.harsh.myrest.service;


import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StringProcessorSevice {

    public boolean isPalindrome(String input){
        String reverse = new StringBuilder(input).reverse().toString();
        return reverse.equals(input);
    }

}
