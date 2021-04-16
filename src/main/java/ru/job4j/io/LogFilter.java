package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines()
                    .filter(x -> x.contains("404"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String lg : log) {
                out.println(lg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("./data/log.txt");
        save(log, "./data/404.txt");
    }
}
