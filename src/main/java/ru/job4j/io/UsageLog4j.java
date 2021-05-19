package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ruslan Davydov";
        int age = 33;
        double weight = 90.5;
        short height = 180;
        byte house = 11;
        long flat = 46L;
        float square = 69.9f;
        char price = '$';
        boolean java = true;
        LOG.debug(
                "User info name : {}, age : {}, weight : {}, height : {}, house : {}, flat : {}, square : {}, price : {}, java",
                name, age, weight, height, house, flat, square, price, java
        );
    }
}
