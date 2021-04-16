package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private String start;
    private String finish;
    private boolean workStatus;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            List<String> listLine = new ArrayList<>();
            StringBuilder result = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                if (!line.isEmpty()) {
                    if ((line.startsWith("400") || line.startsWith("500")) && !workStatus) {
                        start = line.split(" ")[1];
                        workStatus = true;
                    } else if ((line.startsWith("200") || line.startsWith("300")) && workStatus) {
                        finish = line.split(" ")[1];
                        result.append(String.format("%s;%s", start, finish));
                        listLine.add(result.toString());
                        result = new StringBuilder();
                        workStatus = false;
                    }
                }
            }
            for (String line : listLine) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
