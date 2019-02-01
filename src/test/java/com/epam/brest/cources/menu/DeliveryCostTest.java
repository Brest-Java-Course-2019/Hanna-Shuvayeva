package com.epam.brest.cources.menu;

//import com.epam.brest.cources.menu.DeliveryCost;
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


}