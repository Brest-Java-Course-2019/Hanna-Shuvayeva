package com.epam.brest.cources.menu;
import java.math.BigDecimal;
public class DeliveryCost {
       private static final String DISTANCE = "distance";
       private static final String WEIGHT = "weight";

       public static void main(String[] args){
           try{

               BigDecimal distance = ScannerValue.scanValue(DISTANCE);
               BigDecimal weight = ScannerValue.scanValue(WEIGHT);
               ValueImpl value=new ValueImpl();
               value.setWeight(weight);
               value.setDistance(distance);
               BigDecimal valueItem=value.Value().multiply(distance);
               System.out.println("distance: "+distance+", weight :"+weight+", shipping charge: "+valueItem);

           }
           catch (Exception ex){
               System.out.println (ex.getMessage());
           }
       }



}
