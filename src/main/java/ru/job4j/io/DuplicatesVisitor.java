package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> duplicates = new HashSet<>();
    private Set<FileProperty> tracking = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty key = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!tracking.add(key)) {
            duplicates.add(key);
        }
        return FileVisitResult.CONTINUE;
    }
}
