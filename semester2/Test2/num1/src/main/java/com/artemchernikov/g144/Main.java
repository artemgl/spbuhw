package com.artemchernikov.g144;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        SortedSet<LinkedList<String>> set = new SortedSet<>();

        String[] strings = {
                "fff kfoewk kfwop kdn nakfwem ofewk",
                "sdf lfwko niwnfdmsm fnwi",
                "dsfkrjwoj kgwokk",
                " kk k kk k kk kk kk kk kk k k"
        };

        set.add(strings);

        set.print();

    }
}
