package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                String st = br.readLine();
                if (!st.startsWith("#") && !st.isEmpty()) {
                    String[] sp = st.split("=");
                    if (sp.length != 2) {
                        throw new IllegalArgumentException();
                    } else {
                        values.put(sp[0], sp[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
