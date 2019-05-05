package com.artemchernikov.g144;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("What type must be elements of queue?");
        System.out.println("Enter:");
        System.out.println("1 - Integer");
        System.out.println("2 - Double");
        System.out.println("3 - String");

        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        PriorityQueue priorityQueue = null;
        switch (type) {
            case 1:
                priorityQueue = new PriorityQueue<Integer>();
                break;
            case 2:
                priorityQueue = new PriorityQueue<Double>();
                break;
            case 3:
                priorityQueue = new PriorityQueue<String>();
                break;
                default:
                    System.out.println("You entered the wrong data");
                    throw new Exception();
        }

        int input = 0;
        do {
            System.out.println("Enter:");
            System.out.println("1 - add value to priority queue");
            System.out.println("2 - get value from priority queue");
            System.out.println("any number - exit");
            input = in.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter the value and the priority through space");
                    priorityQueue.enqueue(in.next(), in.nextInt());
                    break;
                case 2:
                    try {
                        System.out.println("Value with the largest priority is " + priorityQueue.dequeue());
                    } catch (Exception exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                default:
                    input = 0;
                    break;
            }
        } while (input != 0);
    }
}
