package com.epam.brest.cources.menu;


import com.epam.brest.cources.calc.Calc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class DeliveryCost implements ShippingCost_rate, Calc {
       private  String name;
       private double distance;
       private double weight;
       private double cost_1km;
       private static final Logger LOGGER = LogManager.getLogger();
       public String getName(){
           return name;
       }
       public double getDistance(){
           return distance;
       }
       public double getWeight(){
           return weight;
       }
       public double getCost_1km(){
           return cost_1km;
       }
       @Override
       public double getRate_1() {
            return 1.2;
       }
       @Override
       public double getRate_2() {
            return 1.3;
       }
       @Override
       public double getRate_3() {
           return 1.4;
       }
       @Override
       public double getShipCost_1km() {
           double c = 0;
           if (getDistance() > 1000 && getDistance()<=5000) {
               c = getRate_1() * getCost_1km();
           }
           if (getDistance() > 5000 && getDistance()<=7000) {
               c = getRate_2() * getCost_1km();
           }
           if (getDistance() > 7000) {
               c = getRate_3() * getCost_1km();
           }
           if(getDistance() < 1000){
               c=getCost_1km();
           }
           if(getDistance()==0||getDistance()<0){
               System.out.println("Input Error");
           }
           return c;
       }

       @Override
       public double getShipCost_weight() {
           double cc=0;
           if(getWeight()>=2000 && getDistance()<=8000) {
               cc=550 + getShipCost_1km();
           }
           if(getWeight()>8000) {
               cc=850 + getShipCost_1km();
           }
           if(getWeight()==0||getWeight()<0){
               System.out.println("Input Error");
           }
        return cc;
       }


    public void info(){
           System.out.println("Shipping name-"+getName()+", distance to storage warehouse- "+getDistance()+" meters, cargo weight- "+getWeight()+" kg, transportation cost per 1 km - "+getShipCost_1km()+" dollars");
       }

       public static void main(String[] args){
           Scanner scan=new Scanner(System.in);
           System.out.println("Amount of cargo");
           int count=scan.nextInt();
           DeliveryCost[]array=new DeliveryCost[count];
           System.out.println("Cargo Information:");
           for(int i=0; i<count; i++){
               array[i]=new DeliveryCost();
               array[i].name = scan.next();
               array[i].distance = scan.nextDouble();
               array[i].cost_1km = scan.nextDouble();
               array[i].weight = scan.nextDouble();
           }
           for(int i=0; i<count; i++){
               array[i].info();
               LOGGER.info("Cargo Information:: ", array[i]);
           }
           File file=new File("my_file1.txt");
           try {
               file.createNewFile();
           } catch (IOException ex) {
               System.out.println(ex);
           }
           System.out.println(file.isFile() + "\n" + file.getAbsolutePath());
           File dir = new File("my_file1.txt");
           dir.mkdir();
           System.out.println(dir.isDirectory());
           for(int i=0; i<count; i++){
               if(array[i].weight>=2000){
                   try {
                       String str;
                       str = Double.toString(array[i].getShipCost_weight());
                       FileWriter fileWriter = new FileWriter(file, true);
                       fileWriter.write("Cost for 1 km:");
                       fileWriter.write(str);
                       fileWriter.append('\n');
                       fileWriter.close();
                   } catch (IOException ex) {
                       System.out.println(ex);
                   }
               }
           }
           for(int i=0; i<count; i++){
               array[i].info();
               if(array[i].weight>=2000){
                   System.out.println("if the mass is from 2000 to 8000 or more than 8000, " +
                           "then an adjustment is made for the cost of transporting the cargo for 1 km");

                   try(FileReader reader = new FileReader("my_file1.txt"))
                   {
                       int c;
                       while((c=reader.read())!=-1){
                           System.out.print((char)c);
                       }
                   }
                  catch(IOException ex){

                       System.out.println(ex.getMessage());
                   }
               }
           }



       }


}
