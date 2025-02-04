package com.harsh.myrest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Year;


@SpringBootTest
public class DiscountServiceTest {

    @MockBean
    private DiscountService discountService;

    @Test
    public void testCalculate_Discount_Valid() {
        var discount = discountService.calculateDiscount(10, "thanksgiving");
        Assertions.assertEquals(1f, discount);
    }

    @Test
    public void testCalculate_Discount_ValidYear2025() {
        Mockito.when(discountService.getCurrentYear()).thenReturn(Year.of((2025)));
        var discount = discountService.calculateDiscount(20, "xmas");
        Assertions.assertEquals(5f, discount);
    }

    @Test
    public void testCalculate_Discount_ValidYear2024() {
        Mockito.when(discountService.getCurrentYear()).thenReturn(Year.of((2024)));
        var discount = discountService.calculateDiscount(20, "xmas");
        Assertions.assertEquals(0f, discount);
    }
}
