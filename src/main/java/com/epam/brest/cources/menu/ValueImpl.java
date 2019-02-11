package com.epam.brest.cources.menu;
import com.epam.brest.cources.File.Files;
import com.epam.brest.cources.calc.Calc;
import java.io.Serializable;
import java.math.BigDecimal;
public class ValueImpl implements ShippingCostrate, Calc, Serializable {
    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal cost1km;

    public BigDecimal getCost1km() {
        Files files=new Files();
        return this.cost1km=files.File();
    }
    public BigDecimal getWeight() {
        return this.weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getDistance() {
        return this.distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    public BigDecimal Value(){
        BigDecimal itemCost;
        itemCost=getShipCostweight().add(getShipCost1km());
        return itemCost;
    }
    @Override
    public BigDecimal getShipCostweight() {
        BigDecimal itemCost = BigDecimal.ZERO;
        BigDecimal amont = new BigDecimal(550);
        int v = getWeight().compareTo(new BigDecimal("3000.00"));
        int vv = getWeight().compareTo(new BigDecimal("0.00"));
        if((v == -1 || v == 0) && vv == 1)
            itemCost = amont.add(getCost1km());
        if (vv == 0 || vv == -1)
            System.out.println("Input Error");
        return itemCost;
    }

    @Override
    public BigDecimal getShipCost1km() {
        BigDecimal itemCost = BigDecimal.ZERO;
        int v = getDistance().compareTo(new BigDecimal("2000.00"));
        int vv = getDistance().compareTo(new BigDecimal("0.00"));
        if ((v == -1 || v == 0) && vv == 1)
            itemCost = getRate1().multiply(getCost1km());
        if (v == 1)
            itemCost = getRate2().multiply(getCost1km());
        if (vv == 0 || vv == -1)
            System.out.println("Input Error");
        return itemCost;
    }
    @Override
    public BigDecimal getRate2() {
        return new BigDecimal("1.20");
    }

    @Override
    public BigDecimal getRate1() {
        return new BigDecimal("1.10");
    }
}
