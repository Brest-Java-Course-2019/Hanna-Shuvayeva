package com.epam.brest.cources.File;


    public interface DataWriter {

        byte[] marshal(Object... objects) throws Exception;

        Object[] unmarshal(byte[] bytes) throws Exception;
    }

