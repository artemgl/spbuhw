package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void contains() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        for (int i = 0; i < quantity; i++) {
            assertTrue(tree.contains(i));
        }
        for (int i = - quantity; i < 0; i++) {
            assertFalse(tree.contains(i));
        }
    }

    @Test
    void iterator() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        int i = 0;
        Iterator<Integer> iterator = tree.iterator();
        while (iterator.hasNext()) {
            assertEquals(i++, (int)iterator.next());
            iterator.remove();
        }
        assertTrue(tree.isEmpty());
    }

    @Test
    void toArray() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Object[] actualArray = tree.toArray();

        for (int i = 0; i < quantity; i++) {
            assertEquals(i, actualArray[i]);
        }
    }

    @Test
    void toArrayT() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Integer[] actualArray = new Integer[quantity];
        actualArray = tree.toArray(actualArray);

        Integer[] expectedArray = new Integer[quantity];
        for (int i = 0; i < quantity; i++) {
            expectedArray[i] = i;
        }
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void add() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            assertTrue(tree.add(i));
        }
        for (int i = 0; i < quantity; i++) {
            assertFalse(tree.add(i));
        }
    }

    @Test
    void addAndContains() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        for (int i = 0; i < quantity; i++) {
            assertTrue(tree.contains(i));
        }
    }

    @Test
    void remove() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        for (int i = 0; i < quantity; i++) {
            assertTrue(tree.remove(i));
        }
        for (int i = 0; i < quantity; i++) {
            assertFalse(tree.remove(i));
        }
    }

    @Test
    void removeAndContains() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        for (int i = - quantity / 2; i < quantity / 2; i++) {
            tree.remove(i);
        }

        for (int i = - quantity / 2; i < quantity / 2; i++) {
            assertFalse(tree.contains(i));
        }
    }

    @Test
    void containsAll() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Collection<Integer> list = new LinkedList<>();
        for (int i = 0; i < quantity / 2; i++) {
            list.add(i);
        }

        assertTrue(tree.containsAll(list));
    }

    @Test
    void addAll() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;

        Collection<Integer> list = new LinkedList<>();
        for (int i = 0; i < quantity / 2; i++) {
            list.add(i);
        }

        assertTrue(tree.addAll(list));
    }

    @Test
    void removeAll() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Collection<Integer> list = new LinkedList<>();
        for (int i = 0; i < quantity / 2; i++) {
            list.add(i);
        }

        assertTrue(tree.removeAll(list));
    }

    @Test
    void retainAll() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Collection<Integer> list = new LinkedList<>();
        for (int i = 0; i < quantity / 2; i++) {
            list.add(i);
        }

        assertTrue(tree.retainAll(list));
    }

    @Test
    void retainAll2() {
        Collection<Integer> collection = new LinkedList<>();
        AVLTree<Integer> tree = new AVLTree<>();
        int sizeCollection = 5;
        int sizeTree = 10;
        for (int i = 0; i < sizeTree; i++) {
            tree.add(i);
        }
        for (int i = 0; i < sizeCollection; i++) {
            collection.add(i);
        }
        tree.retainAll(collection);

        for (int i = 0; i < sizeTree; i++) {
            if (i < sizeCollection) {
                assertTrue(tree.contains(i));
            }
            else {
                assertTrue(!tree.contains(i));
            }
        }
    }

    @Test
    void clear() {
        Collection<Integer> tree = new AVLTree<>();
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }
        tree.clear();

        assertTrue(tree.isEmpty());
    }

}