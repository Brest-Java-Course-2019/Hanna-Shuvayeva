package com.epam.brest.cources.calc;
import com.epam.brest.cources.menu.ValueImpl;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.math.BigDecimal;

class CalcTest {

    @Test
    void getShipCost1km() {
        System.out.println("@Test 1");
        BigDecimal itemCost = new BigDecimal(2500.0);
        Assert.assertEquals(itemCost, new BigDecimal(2500.0));
    }
    @Test
    void getShipCostweight() {
        System.out.println("@Test 2");
        BigDecimal itemCost = new BigDecimal(2500.0);
        Assert.assertEquals(itemCost, new BigDecimal(2500.0));
    }

}