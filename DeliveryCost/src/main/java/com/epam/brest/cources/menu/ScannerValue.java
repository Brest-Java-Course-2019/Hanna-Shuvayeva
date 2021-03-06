package com.epam.brest.cources.menu;
import java.math.BigDecimal;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScannerValue {

    private static final Logger LOGGER = LogManager.getLogger();

    public static BigDecimal scanValue (String valueDescription) {

        Scanner scan = new Scanner(System.in);
        BigDecimal scannerValue;

        do {
            System.out.println("Enter " + valueDescription + ":");
            while (!scan.hasNextBigDecimal()) {
                LOGGER.info("Incorrect value:");
                scan.next();
            }
            scannerValue = scan.nextBigDecimal();
            if (scannerValue.compareTo(BigDecimal.ZERO) <= 0) {
                LOGGER.info("Incorrect value: {}", scannerValue);
            }

        } while (scannerValue.compareTo(BigDecimal.ZERO) <= 0);
        return scannerValue;

    }

}
