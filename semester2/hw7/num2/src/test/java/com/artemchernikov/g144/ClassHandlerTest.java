package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ClassHandlerTest {

    @Test
    public void printStructureTest() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> someClass = classLoader.loadClass("com.artemchernikov.g144.ClassForPrintTest");

        ClassHandler classHandler = new ClassHandler();
        classHandler.printStructure(someClass);

        String filePath = "src\\main\\java\\com\\artemchernikov\\g144\\ResultsOfHandling\\ClassForPrintTest.java";

        FileReader in = new FileReader(filePath);
        Scanner scanner = new Scanner(in);

        String[] actualStrings = new String[16];
        for (int i = 0; i < actualStrings.length; i++) {
            actualStrings[i] = scanner.nextLine();
        }

        String[] expectedStrings = {"package com.artemchernikov.g144.ResultsOfHandling;",
                "",
                "",
                "public class ClassForPrintTest {",
                "    private int firstValue = 0;",
                "    private double secondValue = 0;",
                "",
                "    public void incrementFirstValue(){",
                "    }",
                "",
                "    public double getSecondValueDividedByFirstValue(){",
                "        return 0;",
                "    }",
                "",
                "",
                "}"};

        Arrays.sort(expectedStrings);
        Arrays.sort(actualStrings);
        assertArrayEquals(expectedStrings, actualStrings);
    }

    @Test
    public void diffClassesTest() {
        ClassHandler classHandler = new ClassHandler();

        assertFalse(classHandler.diffClasses(Integer.class, Double.class));
        assertTrue(classHandler.diffClasses(Boolean.class, Boolean.class));
    }

    @Test
    public void compareClassWithItsResultOfHandling() throws ClassNotFoundException, IOException {
        ClassLoader expectedClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> expectedClass = expectedClassLoader.loadClass("com.artemchernikov.g144.ClassForHandlingTest");

        ClassHandler classPrinter = new ClassHandler();
        classPrinter.printStructure(expectedClass);

        ClassLoader actualClassLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> actualClass = actualClassLoader.loadClass("com.artemchernikov.g144.ResultsOfHandling.ClassForHandlingTest");
        assertTrue(classPrinter.diffClasses(expectedClass, actualClass));
    }

}