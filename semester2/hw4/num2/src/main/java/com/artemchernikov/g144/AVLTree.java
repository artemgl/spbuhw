package com.artemchernikov.g144;

import java.util.*;

/**A class describing AVL-tree implemented on interface Collection*/
public class AVLTree<E> implements Collection<E> {

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

    /**An auxiliary method returns true if received element exists in the tree and returns false otherwise*/
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

    /**A method returns height of received node*/
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * An auxiliary method adding received element to the tree
     * Returns true if adding was successful and false otherwise
     * */
    private boolean addElement(Node node, E value) {
        if (value.equals(node.value)) {
            return false;
        }

        if (((Comparable)value).compareTo(node.value) < 0) {
            if (node.leftChild != null) {
                if (!addElement(node.leftChild, value)) {
                    return false;
                }
                node.leftChild = node.leftChild.balance();
            } else {
                node.leftChild = new Node(value, null, null, 1);
            }
        } else {
            if (node.rightChild != null) {
                if (!addElement(node.rightChild, value)) {
                    return false;
                }
                node.rightChild = node.rightChild.balance();
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
            root = root.balance();
        } else {
            root = new Node(e, null, null, 1);
        }
        size++;
        return true;
    }

    /**An auxiliary method removes received node and returns tree without this node*/
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

                node.rightChild = removeElement(node.rightChild, current.value);
                return node.balance();
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

    /**
     * An auxiliary method removes received value from the tree which is given by root,
     * and returns tree without this value
     * */
    private Node removeElement(Node node, Object value) {
        if (value.equals(node.value)) {
            return removeElement(node);
        }

        if (((Comparable)value).compareTo(node.value) < 0) {
            if (node.leftChild != null) {
                node.leftChild = removeElement(node.leftChild, value);
                return node.balance();
            }
        } else {
            if (node.rightChild != null) {
                node.rightChild = removeElement(node.rightChild, value);
                return node.balance();
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
     * Returns true if all of these elements existed in the tree
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
                result &= remove(current);
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
        private E value;
        private Node leftChild;
        private Node rightChild;
        private int height;

        private Node(E value, Node leftChild, Node rightChild, int height) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.height = height;
        }

        /**A method updates height of node*/
        private void updateHeight() {
            int leftHeight = height(this.leftChild);
            int rightHeight = height(this.rightChild);
            this.height = (rightHeight > leftHeight ? rightHeight : leftHeight) + 1;
        }

        /**A method returns balanced tree, root of which is this node, by rotating*/
        private Node rotateRight() {
            Node left = this.leftChild;
            this.leftChild = left.rightChild;
            left.rightChild = this;

            this.updateHeight();
            left.updateHeight();

            return left;
        }

        /**A method returns balanced tree, root of which is this node, by rotating*/
        private Node rotateLeft() {
            Node right = this.rightChild;
            this.rightChild = right.leftChild;
            right.leftChild = this;

            this.updateHeight();
            right.updateHeight();

            return right;
        }

        /**A method returns balance factor*/
        private int balanceFactor() {
            return height(this.rightChild) - height(this.leftChild);
        }

        /**A method returns balanced tree, root of which is this node*/
        private Node balance() {
            this.updateHeight();

            switch (this.balanceFactor()) {
                case 2:
                    if (this.rightChild.balanceFactor() == -1) {
                        this.rightChild = this.rightChild.rotateRight();
                    }
                    return this.rotateLeft();
                case -2:
                    if (this.leftChild.balanceFactor() == 1) {
                        this.leftChild = this.leftChild.rotateLeft();
                    }
                    return this.rotateRight();
            }

            return this;
        }
    }

    /**A class describing iterator of AVL-tree*/
    private class AVLTreeIterator implements Iterator<E> {

        private ArrayDeque<E> queue;

        private AVLTreeIterator() {
            queue = new ArrayDeque<>();
            fillQueue(root);
        }

        /**A method fills queue with elements of the tree*/
        private void fillQueue(Node node) {
            if (node == null) {
                return;
            }
            fillQueue(node.leftChild);
            queue.offer(node.value);
            fillQueue(node.rightChild);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            queue.removeIf(e -> !AVLTree.this.contains(e));
            return queue.removeFirst();
        }

        /**A method returns true if removing was successful and false in otherwise*/
        private boolean removeLastReturned(Node node) {
            if (node == null) {
                return false;
            }
            if (removeLastReturned(node.rightChild)) {
                return true;
            }
            if (!queue.contains(node.value)) {
                return AVLTree.this.remove(node.value);
            }
            return removeLastReturned(node.leftChild);
        }

        @Override
        public void remove() {
            queue.removeIf(e -> !AVLTree.this.contains(e));
            if (!removeLastReturned(root)) {
                throw new IllegalStateException();
            }
        }
    }
}
