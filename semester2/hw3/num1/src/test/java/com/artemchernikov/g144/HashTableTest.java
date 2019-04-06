package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void hashAndChangeHashFunction() {
        HashTable hashTable = new HashTable(10);

        hashTable.addElement(15);
        hashTable.addElement(47);

        assertEquals(2, hashTable.getMaxLengthOfList());

        hashTable.changeHashFunction(1);

        assertEquals(1, hashTable.getMaxLengthOfList());
    }

    @Test
    void addElement() {
        int size = 20;

        HashTable hashTable = new HashTable(size);

        for (int i = 0; i < size; i++) {
            hashTable.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            assert(hashTable.exists(i));
        }
    }

    @Test
    void removeElement() {
        int size = 20;

        HashTable hashTable = new HashTable(size);

        for (int i = 0; i < size; i++) {
            hashTable.addElement(i);
        }

        for (int i = 0; i < size; i++) {
            hashTable.removeElement(i);
        }

        assertEquals(0, hashTable.getLoadFactor());
    }

}