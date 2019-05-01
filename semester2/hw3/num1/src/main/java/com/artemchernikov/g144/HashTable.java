package com.artemchernikov.g144;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class describing hash-table
 * Сollision resolution is implemented by chaining method
 * */
public class HashTable {

    private int numberOfHashFunction;
    private int size;
    private int amountOfNotFreeBuckets;
    private int amountOfConflictBuckets;
    private List[] buckets;

    public HashTable(int size) {
        this.size = size;
        buckets = new List[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new List();
        }
        amountOfNotFreeBuckets = 0;
        amountOfConflictBuckets = 0;
        numberOfHashFunction = 0;
    }

    public HashTable() {
        size = 256;
        buckets = new List[256];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new List();
        }
        amountOfNotFreeBuckets = 0;
        amountOfConflictBuckets = 0;
        numberOfHashFunction = 0;
    }

    /**
     * A method receiving number of hash-function and changing current one to new
     * If an unknown number is received, the exception will be thrown
     * Hash-table will be changed as if it always was with new hash-function
     * */
    public void changeHashFunction(int numberOfHashFunction) {
        if (this.numberOfHashFunction != numberOfHashFunction) {
            if (numberOfHashFunction >= 0 && numberOfHashFunction < 2) {
                this.numberOfHashFunction = numberOfHashFunction;
            } else {
                throw new IllegalArgumentException("Unknown number of hash-function: " + numberOfHashFunction);
            }

            amountOfNotFreeBuckets = 0;
            amountOfConflictBuckets = 0;

            List[] earlyBuckets = buckets;
            buckets = new List[size];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new List();
            }

            for (List currentList : earlyBuckets) {
                for (int i = 0; i < currentList.getSize(); i++) {
                    addElement(currentList.getElement(i));
                }
            }
        }
    }

    /**A method calculating hash of received value*/
    private int hash(int value) {
        switch (numberOfHashFunction) {
            case 0:
                value ^= (value << 13);
                value ^= (value >>> 17);
                value ^= (value << 5);
                break;
            case 1:
                value ^= (value * 7);
                value ^= ~(value << 11);
                value ^= (value >> 29);
                break;
                //numberOfHashFunction is private field and might be only between 0 and 1 at method changeHashFunction()
        }

        value %= size;
        value += (value) < 0 ? size : 0;

        return value;
    }

    /**A method returns load factor of the hash-table*/
    public double getLoadFactor() {
        return (double)amountOfNotFreeBuckets / size;
    }

    /**
     * A method adding received value to hash-table
     * If received value already exists, the exception will be thrown
     * */
    public void addElement(int value) {
        if (!exists(value)) {
            int hash = hash(value);
            switch (buckets[hash].getSize()) {
                case 0:
                    amountOfNotFreeBuckets++;
                    break;
                case 1:
                    amountOfConflictBuckets++;
                    break;
            }
            buckets[hash].addElement(value);
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
        try {
            switch (buckets[hash].getSize()) {
                case 1:
                    amountOfNotFreeBuckets--;
                    break;
                case 2:
                    amountOfConflictBuckets--;
                    break;
            }
            buckets[hash].removeElement(value);
        } catch(IllegalArgumentException exc) {
            throw new IllegalArgumentException("Hash-table doesn't contain this value: " + value);
        }
    }

    /**A method returns length of the longest chain*/
    public int getMaxLengthOfList() {
        int result = 0;
        for (List current : buckets) {
            if (current.getSize() > result) {
                result = current.getSize();
            }
        }
        return result;
    }

    /**A method returns true if the hash-table contains received value and false in otherwise*/
    public boolean exists(int value) {
        return buckets[hash(value)].exists(value);
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
