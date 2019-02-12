package com.epam.brest.cources.menu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.math.BigDecimal;


class ValueImplTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeEach");
    }

    @Test
    void getWeight() {
        System.out.println("@Test 1");
        ValueImpl ob=new ValueImpl();
        Assert.assertNull(ob.getWeight());
    }

    @Test
    void getDistance() {
        System.out.println("@Test 2");
        ValueImpl ob=new ValueImpl();
        Assert.assertNull(ob.getDistance());
    }
    @Test
    void getRate1(){
        System.out.println("@Test 3");
        ValueImpl ob=new ValueImpl();
        BigDecimal a = new BigDecimal("1.10");
        Assert.assertEquals(a, ob.getRate1());
    }
    @Test
    void getRate2(){
        System.out.println("@Test 4");
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