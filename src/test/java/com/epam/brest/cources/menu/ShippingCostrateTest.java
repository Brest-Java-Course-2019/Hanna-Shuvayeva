package com.epam.brest.cources.menu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.math.BigDecimal;

class ShippingCostrateTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeEach");
    }
    @Test
    void getRate1(){
        ValueImpl ob=new ValueImpl();
        BigDecimal a = new BigDecimal(1.10);
        BigDecimal actual= a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        Assert.assertEquals(ob.getRate1(), actual);
    }
    @Test
    void getRate2(){
        ValueImpl ob=new ValueImpl();
        BigDecimal a = new BigDecimal(1.20);
        BigDecimal actual= a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        Assert.assertEquals(ob.getRate2(), actual);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

}