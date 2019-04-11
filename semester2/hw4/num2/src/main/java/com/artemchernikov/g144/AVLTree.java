package com.artemchernikov.g144;

import java.util.*;

/**A class describing AVL-tree implemented on interface Collection*/
public class AVLTree<E> implements Collection<E> {

    public AVLTree() {
        root = null;
        size = 0;
    }

    private Node root;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private boolean contains(Node node, Object o) {
        if (node == null) {
            return false;
        }

        if (o.equals(node.value)) {
            return true;
        }

        if (((Comparable)o).compareTo(node.value) < 0) {
            return contains(node.leftChild, o);
        } else {
            return contains(node.rightChild, o);
        }
    }

    /**A method returning true if received element exists in the tree and returns false otherwise*/
    @Override
    public boolean contains(Object o) {
        return contains(root, o);
    }

    /**A method returning object of class AVLTreeIterator*/
    @Override
    public Iterator<E> iterator() {
        return new AVLTreeIterator();
    }

    /**A method returning the array of elements of the tree*/
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (E e : this) {
            result[i++] = e;
        }
        return result;
    }

    /**
     * A method writing elements of the tree into the received array
     *
     * If length of received array is shorter than size of the tree,
     * method will return new array with length which equals the size
     *
     * If length of received array is longer than size of the tree,
     * method will null array element after last element
     * */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[])toArray();
        }
        System.arraycopy(toArray(), 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        int leftHeight = height(node.leftChild);
        int rightHeight = height(node.rightChild);
        node.height = (rightHeight > leftHeight ? rightHeight : leftHeight) + 1;
    }

    private Node rotateRight(Node node) {
        Node left = node.leftChild;
        node.leftChild = left.rightChild;
        left.rightChild = node;

        updateHeight(node);
        updateHeight(left);

        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.rightChild;
        node.rightChild = right.leftChild;
        right.leftChild = node;

        updateHeight(node);
        updateHeight(right);

        return right;
    }

    private int balanceFactor(Node node) {
        return height(node.rightChild) - height(node.leftChild);
    }

    private Node balance(Node node) {
        updateHeight(node);

        switch (balanceFactor(node)) {
            case 2:
                if (balanceFactor(node.rightChild) == -1) {
                    node.rightChild = rotateRight(node.rightChild);
                }
                return rotateLeft(node);
            case -2:
                if (balanceFactor(node.leftChild) == 1) {
                    node.leftChild = rotateLeft(node.leftChild);
                }
                return rotateRight(node);
        }

        return node;
    }

    private boolean addElement(Node node, E value) {
        if (value.equals(node.value)) {
            return false;
        }

        if (((Comparable)value).compareTo(node.value) < 0) {
            if (node.leftChild != null) {
                if (!addElement(node.leftChild, value)) {
                    return false;
                }
                node.leftChild = balance(node.leftChild);
            } else {
                node.leftChild = new Node(value, null, null, 1);
            }
        } else {
            if (node.rightChild != null) {
                if (!addElement(node.rightChild, value)) {
                    return false;
                }
                node.rightChild = balance(node.rightChild);
            } else {
                node.rightChild = new Node(value, null, null, 1);
            }
        }
        return true;
    }

    /**
     * A method adding received element to the tree
     * Returns true if adding was successful and false otherwise
     * */
    @Override
    public boolean add(E e) {
        if (root != null) {
            if (!addElement(root, e)) {
                return false;
            }
            root = balance(root);
        } else {
            root = new Node(e, null, null, 1);
        }
        size++;
        return true;
    }

    private Node removeElement(Node node) {
        if (node.leftChild != null) {
            if (node.rightChild != null) {
                Node current = node.rightChild;

                while (current.leftChild != null) {
                    current = current.leftChild;
                }

                //swap
                E value = node.value;
                node.value = current.value;
                current.value = value;

                removeElement(node.rightChild, current.value);
                return balance(node);
            } else {
                return node.leftChild;
            }
        } else {
            if (node.rightChild != null) {
                return node.rightChild;
            } else {
                return null;
            }
        }
    }

    private Node removeElement(Node node, Object value) {
        if (value.equals(node.value)) {
            return removeElement(node);
        }

        if (((Comparable)value).compareTo(node.value) < 0) {
            if (node.leftChild != null) {
                node.leftChild = removeElement(node.leftChild, value);
                return balance(node);
            }
        } else {
            if (node.rightChild != null) {
                node.rightChild = removeElement(node.rightChild, value);
                return balance(node);
            }
        }
        return node;
    }

    /**
     * A method removing received element from the tree
     * Returns true if removing was successful and false otherwise
     * */
    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            root = removeElement(root, o);
            size--;
            return true;
        }
        return false;
    }

    /**
     * A method returning true if all of the elements from received collection
     * exist in the tree and false otherwise
     * */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method adding all of the elements from received collection to the tree
     * Returns true if none of these elements existed in the tree before adding,
     * and false otherwise
     * */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = true;
        for (E current : c) {
            result &= add(current);
        }
        return result;
    }

    /**
     * A method removing all of the elements from received collection from the tree
     * Returns true if all of these elements existed in the tree before removing,
     * and false otherwise
     * */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = true;
        for (Object current : c) {
            result &= remove(current);
        }
        return result;
    }

    /**
     * A method removing all of the elements from the tree,
     * except those contained in the received collection
     * Returns true if all of these elements existed in the tree before removing,
     * and false otherwise
     * */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = true;
        for (Object current : c) {
            if (!contains(current)) {
                result = false;
            }
        }
        for (E current : this) {
            if (!c.contains(current)) {
                remove(current);
            }
        }
        return result;
    }

    /**A method removing all of the elements from the tree*/
    @Override
    public void clear() {
        for (E e : this) {
            remove(e);
        }
    }

    /**A class describing node of AVL-tree*/
    private class Node {
        private Node(E value, Node leftChild, Node rightChild, int height) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.height = height;
        }
        E value;
        Node leftChild;
        Node rightChild;
        int height;
    }

    /**A class describing iterator of AVL-tree*/
    private class AVLTreeIterator implements Iterator<E> {

        private AVLTreeIterator() {
            position = 0;
            list = new LinkedList<>();
            addBranchToList(root);
        }

        private void addBranchToList(Node node) {
            if (node == null) {
                return;
            }
            addBranchToList(node.leftChild);
            list.add(node);
            addBranchToList(node.rightChild);
        }

        LinkedList<Node> list;
        int position;

        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public E next() {
            if (hasNext()) {
                return list.get(position++).value;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (position == 0 || position > list.size()) {
                throw new IllegalStateException();
            }
            AVLTree.this.remove(list.get(position - 1).value);
        }
    }
}
