package com.epam.brest.cources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class DeliveryCost implements ShippingCost_rate{
       private String name;
       private double distance;
       private double weight;
       private double cost_1km;

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
           return c;
       }

       @Override
       public double getShipCost_weight() {
           double cc=0;
           if(getWeight()>2000 && getDistance()<=8000) {
               cc=550 + getCost_1km();
           }
           if(getWeight()>8000) {
               cc=850 + getCost_1km();
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
                   if(array[i].weight>=1000){
                       try {
                           String str;
                           str = Double.toString(array[i].getShipCost_weight());
                           FileWriter fileWriter = new FileWriter(file, true);
                           fileWriter.write(str);
                           fileWriter.append('\n');
                           fileWriter.close();
                       } catch (IOException ex) {
                           System.out.println(ex);
                       }
                   }
               }
           //try{
               //FileInputStream fstream = new FileInputStream("D://my_file1");
               //BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
               //String strLine;
               //if(array[i].weight>=1000) {
                   //System.out.println("Цена груза "+br.readLine());
               //}
               //fstream.close();
          // }catch (IOException e){
              // System.out.println(ex);
           //}


           //try {
               //FileReader fileReader = new FileReader(file);
               //Scanner scan=new Scanner(System.in);
               ////int c;
               //while ( (c = fileReader.read()) != -1){
                   //System.out.print((char) c);}
              // fileReader.close();
           //} catch (FileNotFoundException ex) {
               //System.out.println("ex");
          // } catch (IOException ex) {
               //Logger.getLogger(AnnaShuvaevaJava8.class.getName()).log(Level.SEVERE, null, ex);
           //}

// FileReader fileReader;
// try {
// fileReader = new FileReader(file);
// Scanner scanner = new Scanner(fileReader);
// int i = 0;
// while(scanner.hasNext()) {
// System.out.println(i++ + scanner.nextLine());
// }
// fileReader.close();
// } catch (FileNotFoundException ex) {
// Logger.getLogger(AnnaShuvaevaJava8.class.getName()).log(Level.SEVERE, null, ex);
// } catch (IOException ex) {
// Logger.getLogger(AnnaShuvaevaJava8.class.getName()).log(Level.SEVERE, null, ex);
// }
       }


}
