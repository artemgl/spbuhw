package com.artemchernikov.g144;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A class describing hash-table
 * Сollision resolution is implemented by chaining method
 * */
public class HashTable<E> {

    private Function<E, Integer> hashFunction;
    private int size;
    private int amountOfConflictBuckets;
    private int amountOfElements;
    private ArrayList<E>[] buckets;

    public HashTable(int size) {
        this.size = size;
        buckets = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ArrayList<E>();
        }
        amountOfConflictBuckets = 0;
        amountOfElements = 0;
        hashFunction = e -> {
            int n = e.hashCode();
            n ^= (n << 13);
            n ^= (n >>> 17);
            n ^= (n << 5);
            return n;};
    }

    /**A method calculating hash of received value*/
    private int hash(E value) {
        int result = hashFunction.apply(value);

        result %= size;
        result += (result) < 0 ? size : 0;

        return result;
    }

    /**
     * A method returns load factor of the hash-table
     * @return load factor of the hash-table
     * */
    public double getLoadFactor() {
        return (double)amountOfElements / size;
    }

    /**An auxiliary method clears buckets and changes size of buckets to received one*/
    private void clearAndUpdate(int newSize) {
        amountOfConflictBuckets = 0;
        amountOfElements = 0;
        size = newSize;

        buckets = new ArrayList[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<E>();
        }
    }

    /**
     * A method receiving hash-function and changing current one to new one
     * Hash-table will be changed as if it always was with new hash-function
     * @param newHashFunction function to change
     * */
    public void changeHashFunction(Function<E, Integer> newHashFunction) {
        ArrayList<E>[] earlyBuckets = buckets;
        clearAndUpdate(size);

        hashFunction = newHashFunction;
        for (ArrayList<E> currentList : earlyBuckets) {
            for (E n : currentList) {
                addElement(n);
            }
        }
    }

    /**
     * A method expands hash-table 2 times
     * If number of buckets too big, expands to size 2 147 483 647 (Integer.MAX_VALUE)
     * */
    private void expand() {
        ArrayList<E>[] earlyBuckets = buckets;

        if (size > Integer.MAX_VALUE / 2) {
            clearAndUpdate(Integer.MAX_VALUE);
        } else {
            clearAndUpdate(size * 2);
        }

        for (ArrayList<E> currentList : earlyBuckets) {
            for (E n : currentList) {
                addElement(n);
            }
        }
    }

    /**
     * A method adding element to hash-table
     * If load factor is bigger, than 0.999, the hash-table expands
     * @param value element to add
     * @throws IllegalArgumentException if received value already exists in the hash-table
     * */
    public void addElement(E value) {
        if (exists(value)) {
            throw new IllegalArgumentException("Hash-table already contains this value: " + value);
        }

        int hash = hash(value);
        if (buckets[hash].size() == 1) {
            amountOfConflictBuckets++;
        }
        buckets[hash].add(value);
        amountOfElements++;

        if (getLoadFactor() > 0.999) {
            expand();
        }
    }

    /**
     * A method removing element from hash-table
     * @param value element to remove
     * @throws IllegalArgumentException if received value doesn't exist in the hash-table
     * */
    public void removeElement(E value) {
        int hash = hash(value);
        if (exists(value)) {
            if (buckets[hash].size() == 2) {
                amountOfConflictBuckets--;
            }
            buckets[hash].remove(value);
            amountOfElements--;
        } else {
            throw new IllegalArgumentException("Hash-table doesn't contain this value: " + value);
        }
    }

    /**
     * A method returns length of the longest chain
     * @return length of the longest chain
     * */
    public int getMaxLengthOfList() {
        int result = 0;
        for (ArrayList current : buckets) {
            if (current.size() > result) {
                result = current.size();
            }
        }
        return result;
    }

    /**
     * A method checks if element exists in the hash-table
     * @param value element to check
     * @return true if the hash-table contains received value and false in otherwise
     * */
    public boolean exists(E value) {
        return buckets[hash(value)].contains(value);
    }

    /**A method displaying statistics of hash-table*/
    public void showStatistics() {
        System.out.println("Statistics:");
        System.out.println("Size: " + this.size);
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Amount of conflict situations: " + amountOfConflictBuckets);
        System.out.println("Maximum length of list at conflict situation: " + getMaxLengthOfList());
    }

    /**
     * A method filling hash-table with file contents
     * @param fileName path to needed file
     * @throws IOException if reading failed
     * */
    public void fillFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner fin = new Scanner(file);
        while (fin.hasNextInt()) {
            addElement((E)fin.next());
        }
    }

}
