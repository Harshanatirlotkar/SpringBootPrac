package com.harsh.myrest.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringProcessorSeviceTest {

    @Autowired
    private StringProcessorSevice stringProcessorSevice;


    @Test
    public void TestIsPalindrome_Valid() {
        var result = stringProcessorSevice.isPalindrome("adada");
        Assertions.assertTrue(result);

    }

    @Test
    public void TestIsPalindrome_InValid() {
        var result = stringProcessorSevice.isPalindrome("3fweweg");
        Assertions.assertFalse(result);

    }

    @Test
    public void TestIsPalindrome_Null() {
        Assertions.assertThrows(NullPointerException.class , () -> stringProcessorSevice.isPalindrome(null));

    }

}
