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
        ValueImpl ob=new ValueImpl();
        Assert.assertNull(ob.getWeight());
    }

    @Test
    void getDistance() {
        ValueImpl ob=new ValueImpl();
        Assert.assertNull(ob.getWeight());
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

    //@Test
    //void value() {
    //}

    //@Test
    //void getShipCostweight() {
    //}

    //@Test
    //void getShipCost1km() {
    //}

    //@Test
    //void getRate2() {
    //}

    //@Test
    //void getRate1() {
    //}
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }
}