package com.artemchernikov.g144;

public class Main {
    public static void main(String[] argc) {
        List MyList = new List();

        for (int i = 0; i < 10; i++) {
            MyList.addElement(i + 1);
        }

        for (int i = 0; i < 10; i += 2) {
            MyList.removeElement(i + 1);
        }

        System.out.println(MyList.exists(7));

        MyList.print();
    }
}
