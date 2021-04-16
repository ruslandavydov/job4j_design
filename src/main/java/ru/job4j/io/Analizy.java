package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private String start;

    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while (in.ready()) {
                if (in.readLine().startsWith("400") || in.readLine().startsWith("500")) {
                    start = in.readLine().split(" ")[1];
                } else if (in.readLine().startsWith("200") || in.readLine().startsWith("300")) {
                    String finish = in.readLine().split(" ")[1];
                    rsl.append(String.format("%s;%s;%s", start, finish, System.lineSeparator()));
                    lines.add(rsl.toString());
                    rsl = new StringBuilder();
                }
            }
            writeFile(lines, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(List<String> lines, String target) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String line : lines) {
                out.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
