package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("/Users/Nastya/projects");
        Files.walkFileTree(start, new SearchFiles());
    }
}
