package com.epam.brest.cources.File;
import com.epam.brest.cources.menu.ValueImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class SerializationTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void test() throws Exception {
        DataWriter writer = new JSONWriter();
        //byte[] bytes = writer.marshal(create(), create());
        String t="1250.0";
        byte[] bytes = t.getBytes();
        LOGGER.info("Data: {}", t);
        Object[] result = writer.unmarshal(bytes);
        LOGGER.info("Result: {}", Arrays.toString(result));
    }

    private ValueImpl create() {
        ValueImpl valueImpl = new ValueImpl();
        valueImpl.setWeight(new BigDecimal("1"));
        valueImpl.setDistance(new BigDecimal("2"));
        return valueImpl;
    }

}