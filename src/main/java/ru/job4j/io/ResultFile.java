package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int two = 2;
        int four = 4;
        int twoTimesFour = two * four;
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Two times Four equals ".getBytes());
            out.write(String.valueOf(twoTimesFour).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
