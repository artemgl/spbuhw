package com.artemchernikov.g144;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ClassForHandlingTest {
    private LinkedList<Integer> list = new LinkedList<>();
    private Map<String, Boolean> map = new HashMap<>();
    private int[] array = {};

    public int[] getArray() {
        return array;
    }

    public boolean getValue(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return false;
    }

    public void setList(LinkedList<Integer> list) {
        this.list = list;
    }
}