package com.epam.brest.cources.File;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
public class Files {
    public BigDecimal File() {
        File file = new File("my_file1.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(file.isFile() + "\n" + file.getAbsolutePath());
        File dir = new File("my_file1.txt");
        dir.mkdir();
        System.out.println(dir.isDirectory());
        try {
            BigDecimal bd = new BigDecimal("1250");
            String str = String.valueOf(bd.doubleValue());
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(str);
            fileWriter.append('\n');
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String b = null;
        try (FileReader reader = new FileReader("my_file1.txt")) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {

                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
            b = new String(buf);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return new BigDecimal(Double.valueOf(b));
    }
}



