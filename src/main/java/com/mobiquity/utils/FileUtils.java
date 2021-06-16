package com.mobiquity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class FileUtils {

    public static Stream<String> getLines(String filePath) throws NoSuchFileException {
        try {
            BufferedReader reader =
                    Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8);
            return reader.lines();
        } catch (IOException ex) {
            throw  new NoSuchFileException("file not found at path:"+filePath);
        }
    }
}
