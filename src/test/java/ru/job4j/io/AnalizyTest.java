package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            assertThat(in.readLine(), is("10:57:01;10:59:01"));
            assertThat(in.readLine(), is("11:01:02;11:02:02"));
        }
    }

    @Test
    public void whenReadFileThenFindTwoPeriod() {
        String source = "./data/server.log";
        String target = "./data/unavailable.csv";
        new Analizy().unavailable(source, target);
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            assertThat(read.readLine(), is("10:57:01;10:59:01"));
            assertThat(read.readLine(), is("11:01:02;11:02:02"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
