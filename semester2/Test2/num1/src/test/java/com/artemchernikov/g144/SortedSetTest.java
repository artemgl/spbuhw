package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SortedSetTest {

    @Test
    void compare() {
        LinkedList<String> first = new LinkedList<>();
        LinkedList<String> second = new LinkedList<>();

        first.add("fewf");
        first.add("fewfe");
        first.add("htrjyjuk");
        first.add("fkwronv");
        first.add("Fkweo");

        second.add("Fwg");
        second.add("gwrgvw");
        second.add("gvrwb");
        second.add("hebn");

        SortedSet<LinkedList<String>> set = new SortedSet<>();

        assertTrue(set.compare(first, second) > 0);

        second.add("fggg");

        assertEquals(0, set.compare(first, second));

        second.add("fgwgwgwgwg");

        assertTrue(set.compare(first, second) < 0);
    }

}