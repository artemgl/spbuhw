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
public class HashTable {

    private Function<Integer, Integer> hashFunction;
    private int size;
    private int amountOfConflictBuckets;
    private int amountOfElements;
    private ArrayList[] buckets;

    public HashTable(int size) {
        this.size = size;
        buckets = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ArrayList<>();
        }
        amountOfConflictBuckets = 0;
        amountOfElements = 0;
        hashFunction = n -> {
            n ^= (n << 13);
            n ^= (n >>> 17);
            n ^= (n << 5);
            return n;};
    }

    /**A method calculating hash of received value*/
    private int hash(int value) {
        value = hashFunction.apply(value);

        value %= size;
        value += (value) < 0 ? size : 0;

        return value;
    }

    /**A method returns load factor of the hash-table*/
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
            buckets[i] = new ArrayList<Integer>();
        }
    }

    /**
     * A method receiving hash-function and changing current one to new one
     * Hash-table will be changed as if it always was with new hash-function
     * */
    public void changeHashFunction(Function<Integer, Integer> newHashFunction) {
        ArrayList<Integer>[] earlyBuckets = buckets;
        clearAndUpdate(size);

        hashFunction = newHashFunction;
        for (ArrayList<Integer> currentList : earlyBuckets) {
            for (Integer n : currentList) {
                addElement(n);
            }
        }
    }

    /**
     * A method expands hash-table 2 times
     * If number of buckets too big, expands to size 2 147 483 647
     * */
    private void expand() {
        ArrayList<Integer>[] earlyBuckets = buckets;

        if (size > 0x3FFFFFFF) {
            clearAndUpdate(0x7FFFFFFF);
        } else {
            clearAndUpdate(size * 2);
        }

        for (ArrayList<Integer> currentList : earlyBuckets) {
            for (Integer n : currentList) {
                addElement(n);
            }
        }
    }

    /**
     * A method adding received value to hash-table
     * If received value already exists, the exception will be thrown
     * If load factor is bigger, than 0.999, the hash-table expands
     * */
    public void addElement(int value) {
        if (!exists(value)) {
            int hash = hash(value);
            if (buckets[hash].size() == 1) {
                amountOfConflictBuckets++;
            }
            buckets[hash].add(value);
            amountOfElements++;

            if (getLoadFactor() > 0.999) {
                expand();
            }
        } else {
            throw new IllegalArgumentException("Hash-table already contains this value: " + value);
        }
    }

    /**
     * A method removing received value from hash-table
     * If received value doesn't exist, the exception will be thrown
     * */
    public void removeElement(int value) {
        int hash = hash(value);
        if (exists(value)) {
            if (buckets[hash].size() == 2) {
                amountOfConflictBuckets--;
            }
            buckets[hash].remove(Integer.valueOf(value));
            amountOfElements--;
        } else {
            throw new IllegalArgumentException("Hash-table doesn't contain this value: " + value);
        }
    }

    /**A method returns length of the longest chain*/
    public int getMaxLengthOfList() {
        int result = 0;
        for (ArrayList current : buckets) {
            if (current.size() > result) {
                result = current.size();
            }
        }
        return result;
    }

    /**A method returns true if the hash-table contains received value and false in otherwise*/
    public boolean exists(int value) {
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

    /**A method receiving way to file and filling hash-table with file contents*/
    public void fillFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner fin = new Scanner(file);
        while (fin.hasNextInt()) {
            addElement(fin.nextInt());
        }
    }

}
