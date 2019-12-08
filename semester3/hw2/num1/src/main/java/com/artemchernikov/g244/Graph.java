package com.artemchernikov.g244;

import java.util.ArrayList;

/**A class describing oriented graph*/
public class Graph<T> {

    private ArrayList<ArrayList<T>> vertices = new ArrayList<>();

    /**
     * A method adds relation between vertices
     * @param first first vertex
     * @param second second vertex
     * @return true if relation added successfully, and false otherwise
     * */
    public boolean addRelation(T first, T second) {
        for (ArrayList<T> list : vertices) {
            if (list.get(0) == first) {
                if (!list.contains(second)) {
                    list.add(second);
                    addRelation(second, first);
                    return true;
                } else {
                    return false;
                }
            }
        }

        ArrayList<T> newList = new ArrayList<>();
        newList.add(first);
        newList.add(second);
        vertices.add(newList);
        addRelation(second, first);
        return true;
    }

    /**
     * A method checks if vertices are related
     * @param first first vertex to check
     * @param second second vertex to check
     * @return true if relation between received vertices exists, and false otherwise
     * */
    public boolean areRelated(T first, T second) {
        for (ArrayList<T> list : vertices) {
            if (list.get(0) == first && list.contains(second)) {
                return true;
            }
        }
        return false;
    }

    /**A method displays graph to console*/
    public void print() {
        for (ArrayList<T> list : vertices) {
            System.out.print(list.get(0).toString() + ": ");
            for (int i = 1; i < list.size(); i++) {
                System.out.print(list.get(i).toString() + ", ");
            }
            System.out.println();
        }
    }

}
