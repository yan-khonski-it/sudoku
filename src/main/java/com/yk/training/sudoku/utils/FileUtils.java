package com.yk.training.sudoku.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException("Instanced not allowed.");
    }

    public static List<String> readResourceFile(final String filename) {
        return readResourceFile(filename, FileUtils.class);
    }

    public static List<String> readResourceFile(final String filename, final Class<?> clazz) {
        final InputStream is = clazz.getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            throw new RuntimeException(String.format("Failed to open resource: %s.", filename));
        }

        final List<String> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = br.readLine();
            while (line != null) {
                res.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to read file %s.", filename), e);
        }

        return res;
    }
}
