package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, Path> paths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty key = new FileProperty(file.toFile().length(), file.toFile().getName());
        paths.put(key, file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }
}
