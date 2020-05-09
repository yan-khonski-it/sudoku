package com.yk.training.sudoku.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {

    public static List<String> LINES = List.of(
            "0 1 3 8 0 0 4 0 5",
            "0 2 4 6 0 5 0 0 0",
            "0 8 7 0 0 0 9 3 0",
            "4 9 0 3 0 6 0 0 0",
            "0 0 1 0 0 0 5 0 0",
            "0 0 0 7 0 1 0 9 3",
            "0 6 9 0 0 0 7 4 0",
            "0 0 0 2 0 7 6 8 0",
            "1 0 2 0 0 8 3 5 0"
    );

    @Test
    public void shouldReadFile() {
        List<String> actualLines = FileUtils.readResourceFile("matrix/testMatrix.txt", FileUtilsTest.class);

        assertEquals(LINES, actualLines);
    }

}