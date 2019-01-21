package com.epam.brest.cources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DeliveryCost{
       private String name;
       private int distance;
       private int weight;
       private int cost;
       public String getName(){
           return name;
       }
       public int getDistance(){
           return distance;
       }
       public int getWeight(){
           return weight;
       }
       public int getCost(){
           return cost;
       }
       public void info(){
           System.out.println("Наименование груза-"+getName()+", расстояние до склада хранения- "+getDistance()+" метров, масса груза- "+getWeight()+" кг, цена груза - "+getCost()+" рублей");
       }

       public static void main(String[] args){
           Scanner scan=new Scanner(System.in);
           System.out.println("Введите количество грузов");
           int count=scan.nextInt();
           DeliveryCost[]array=new DeliveryCost[count];
           System.out.println("Введите все параметры груза");
           for(int i=0; i<count; i++){
               array[i]=new DeliveryCost();
               array[i].name = scan.next();
               array[i].distance = scan.nextInt();
               array[i].cost = scan.nextInt();
               array[i].weight = scan.nextInt();
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
           File dir = new File("D://my_file1");
           dir.mkdir();
           System.out.println(dir.isDirectory());
               for(int i=0; i<count; i++){
                   if(array[i].weight>=1000){
                       try {
                           array[i].cost=52000;
                           FileWriter fileWriter = new FileWriter(file, true);
                           //fileWriter.write(t);
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
