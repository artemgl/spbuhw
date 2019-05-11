package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.artemchernikov.g144.SecondPartTasks.*;
import static org.junit.jupiter.api.Assertions.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(
                List.of(
                        "fdagdsbkjsequencerjwivojrwbfs",
                        "kfkadsjnssequence",
                        "sequencekjfagbievppwq",
                        "fdagdsbkjsequencerjwivojrwbfs",
                        "okdfpgsklsequencelakgnd.jngds[df"),
                findQuotes(
                        List.of(
                                "testFindQuotes1.txt",
                                "testFindQuotes2.txt",
                                "testFindQuotes3.txt"), "sequence"));

        assertEquals(
                Collections.emptyList(),
                findQuotes(
                        List.of(
                                "testFindQuotes1.txt",
                                "testFindQuotes2.txt",
                                "testFindQuotes3.txt"), "java"));

        assertEquals(
                Collections.emptyList(),
                findQuotes(Collections.emptyList(), ""));
    }

    @Test
    public void testPiDividedBy4() {
        int countOfTests = 3;

        final double epsilon = 0.001;

        for (int i = 0; i < countOfTests; i++) {
            assertEquals(Math.PI/4, piDividedBy4(), epsilon);
        }
    }

    @Test
    public void testFindPrinter() {
        assertEquals(
                "Robert",
                findPrinter(Map.of(
                        "Paul", List.of("One", "Two", "Three", "Four"),
                        "Robert", List.of("First", "Second", "Third"),
                        "Michael", List.of("123", "456", "789"))));

        assertEquals(
                "",
                findPrinter(Collections.emptyMap()));
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
                Map.of(
                        "Apples", 1484,
                        "Carrots", 1659,
                        "Cucumbers", 893,
                        "Pears", 390,
                        "Potatoes", 2099,
                        "Tomatoes", 1201),
                calculateGlobalOrder(List.of(
                        Map.of(
                                "Carrots", 322,
                                "Pears", 340),
                        Map.of(
                                "Potatoes", 778,
                                "Tomatoes", 726),
                        Map.of(
                                "Apples", 517,
                                "Carrots", 408,
                                "Cucumbers", 674),
                        Map.of(
                                "Carrots", 565,
                                "Tomatoes", 11),
                        Map.of(
                                "Apples", 967,
                                "Carrots", 121,
                                "Pears", 50,
                                "Potatoes", 647),
                        Map.of(
                                "Carrots", 243,
                                "Cucumbers", 219,
                                "Potatoes", 674,
                                "Tomatoes", 464))));

        assertEquals(
                Collections.emptyMap(),
                calculateGlobalOrder(Collections.emptyList()));
    }
}

