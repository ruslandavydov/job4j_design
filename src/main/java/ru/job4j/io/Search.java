package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private String rootDirectory;
    private String fileExtension;

    public Search(String rootDirectory, String fileExtension) {
        this.rootDirectory = rootDirectory;
        this.fileExtension = fileExtension;
    }

    public static void main(String[] args) throws IOException {
        Search file = new Search(args[0], "java");
        Path start = Paths.get(file.rootDirectory);
        search(start, path -> path
                .toFile()
                .getName()
                .endsWith(file.fileExtension))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
