package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private String start;
    private String finish;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            List<String> lines = new ArrayList<>();
            StringBuilder rsl = new StringBuilder();
            while (in.ready()) {
                if (in.readLine().startsWith("400") || in.readLine().startsWith("500")) {
                    start = in.readLine().split(" ")[1];
                } else if (in.readLine().startsWith("200") || in.readLine().startsWith("300")) {
                    finish = in.readLine().split(" ")[1];
                }
                rsl.append(String.format("%s;%s", start, finish));
                lines.add(rsl.toString());
                rsl = new StringBuilder();
            }
            for (String line : lines) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
