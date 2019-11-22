package com.artemchernikov.g244;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void iterator() {
        AVLTree<Integer> tree = new AVLTree<>();
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
    void twoParallelIterators1() {
        AVLTree<Integer> tree = new AVLTree<>();
        int quantity = 5;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Iterator<Integer> iterator1 = tree.iterator();
        Iterator<Integer> iterator2 = tree.iterator();

        //0, 1, 2, 3, 4
        iterator1.next();
        iterator1.next();
        iterator1.next();

        iterator2.next();

        iterator1.remove();
        iterator1.remove();
        //0, 3, 4

        assertEquals(Integer.valueOf(3), iterator2.next());
    }

    @Test
    void twoParallelIterators2() {
        AVLTree<Integer> tree = new AVLTree<>();
        int quantity = 5;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Iterator<Integer> iterator1 = tree.iterator();
        Iterator<Integer> iterator2 = tree.iterator();

        //0, 1, 2, 3, 4
        iterator1.next();
        iterator1.next();

        iterator2.next();
        iterator2.next();
        iterator2.next();

        iterator1.remove();
        //0, 2, 3, 4

        iterator2.remove();
        //0, 3, 4

        assertEquals(Integer.valueOf(3), iterator1.next());
    }

    @Test
    void threeParallelIterators() {
        AVLTree<Integer> tree = new AVLTree<>();
        int quantity = 5;
        for (int i = 0; i < quantity; i++) {
            tree.add(i);
        }

        Iterator<Integer> iterator1 = tree.iterator();
        Iterator<Integer> iterator2 = tree.iterator();
        Iterator<Integer> iterator3 = tree.iterator();

        //0, 1, 2, 3, 4
        iterator1.next();
        iterator1.next();

        iterator2.next();
        iterator2.next();

        iterator1.remove();
        iterator2.remove();
        //2, 3, 4

        assertEquals(Integer.valueOf(2), iterator3.next());
    }

    @Test
    void contains() {
        AVLTree<Integer> tree = new AVLTree<>();
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
    void add() {
        AVLTree<Integer> tree = new AVLTree<>();
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
        AVLTree<Integer> tree = new AVLTree<>();
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
        AVLTree<Integer> tree = new AVLTree<>();
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
        AVLTree<Integer> tree = new AVLTree<>();
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

}