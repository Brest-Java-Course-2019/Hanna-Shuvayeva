package com.epam.brest.cources.menu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
class ValueImplTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeEach");
    }

    @Test
    void getWeight() {
        Assert.assertTrue("null", true);
        Assert.assertFalse("tyr", false);
    }

    @Test
    void setWeight() {
        Assert.assertTrue("null", true);
        Assert.assertFalse("tyr", false);
    }

    @Test
    void getDistance() {
        Assert.assertTrue("null", true);
        Assert.assertFalse("tyr", false);
    }

    @Test
    void setDistance() {
        Assert.assertTrue("null", true);
        Assert.assertFalse("tyr", false);
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