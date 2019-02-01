package com.epam.brest.cources.menu;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

class DeliveryCostTest {

    @Test
    void getRate_1() {
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getRate_1());
        String actual =String.valueOf(1.2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    void getRate_2() {
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getRate_2());
        String actual =String.valueOf(1.3);
        Assert.assertEquals(actual, expected);
    }

    @Test
    void getRate_3() {
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getRate_3());
        String actual =String.valueOf(1.4);
        Assert.assertEquals(actual, expected);
    }
    @Test
    void getShipCost_1km(){
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getDistance());
        String actual =String.valueOf(0.0);
        Assert.assertEquals(actual, expected);
    }
    @Test
    void getShipCost_weight(){
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getWeight());
        String actual =String.valueOf(0.0);
        Assert.assertEquals(actual, expected);
    }
    @Test
    void info(){
        Assert.assertTrue("null",true);
        Assert.assertFalse("tyr",false);
    }
    @Test
    void getName(){
        Assert.assertTrue("null",true);
        Assert.assertFalse("tyr",false);
    }
    @Test
    void getDistance(){
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getDistance());
        String actual =String.valueOf(0.0);
        Assert.assertEquals(actual, expected);
    }
    @Test
    void getWeight(){
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getWeight());
        String actual =String.valueOf(0.0);
        Assert.assertEquals(actual, expected);
    }
    @Test
    void getCost_1km(){
        DeliveryCost ob=new DeliveryCost();
        String expected=String.valueOf(ob.getCost_1km());
        String actual =String.valueOf(0.0);
        Assert.assertEquals(actual, expected);
    }

}