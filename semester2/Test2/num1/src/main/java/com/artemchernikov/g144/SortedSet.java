package com.artemchernikov.g144;

import java.util.ArrayList;
import java.util.LinkedList;

/**A class describing sorted set of lists*/
public class SortedSet<L extends LinkedList> implements ListsComparator {

    private Node root;

    /**{@inheritDoc}*/
    @Override
    public int compare(LinkedList first, LinkedList second) {
        return first.size() - second.size();
    }

    /**
     * A method forms linked lists with words of strings and adds them to set
     * @param strings array of strings to add
     * */
    public void add(String[] strings) {
        for (String string : strings) {
            ArrayList<Integer> indexesWithSpaces = new ArrayList<>();

            char[] charArray = string.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == ' ') {
                    indexesWithSpaces.add(i);
                }
            }

            LinkedList<String> list = new LinkedList<>();
            list.add(string.substring(0, indexesWithSpaces.get(0)));
            for (int i = 0; i < indexesWithSpaces.size(); i++) {
                if (i + 1 == indexesWithSpaces.size()) {
                    list.add(string.substring(indexesWithSpaces.get(i)));
                } else {
                    list.add(string.substring(indexesWithSpaces.get(i), indexesWithSpaces.get(i + 1)));
                }
            }

            add((L)list);
        }
    }

    /**
     * A method adds list to the set
     * @param list list to add
     * */
    public void add(L list) {
        if (root == null) {
            root = new Node((LinkedList)list.clone(), null);
            return;
        }

        Node previous = root;
        Node current = root;
        while (current != null) {
            if (compare(current.list, list) > 0) {
                previous = current;
                current = current.next;
            } else {
                if (previous == current) {
                    root = new Node((LinkedList)list.clone(), root);
                } else {
                    previous.next = new Node((LinkedList)list.clone(), previous.next);
                }
                return;
            }
        }

        previous.next = new Node((LinkedList)list.clone(), null);
    }

    /**A method prints the set*/
    public void print() {
        Node current = root;
        while (current != null) {
            System.out.println(current.list);
            current = current.next;
        }
    }

    /**A class describing node of the set*/
    private class Node {
        private LinkedList list;
        private Node next;

        private Node(LinkedList list, Node next) {
            this.list = list;
            this.next = next;
        }
    }

}
