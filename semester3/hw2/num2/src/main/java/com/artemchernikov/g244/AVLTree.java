package com.artemchernikov.g244;

import java.util.ArrayDeque;
import java.util.Iterator;

/**A class describing AVL-tree*/
public class AVLTree<E> implements Iterable<E> {

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**An auxiliary method returns true if received element exists in the tree, and false otherwise*/
    private boolean contains(Node node, E element) {
        if (node == null) {
            return false;
        }

        if (element.equals(node.value)) {
            return true;
        }

        if (((Comparable)element).compareTo(node.value) < 0) {
            return contains(node.leftChild, element);
        } else {
            return contains(node.rightChild, element);
        }
    }

    /**
     * A method checks if element exists in the tree
     * @param element element to check
     * @return true if element exists in the tree, and false otherwise
     * */
    public boolean contains(E element) {
        return contains(root, element);
    }

    /**
     * A method returns iterator of the tree
     * @return iterator
     * */
    public Iterator<E> iterator() {
        return new AVLTreeIterator();
    }

    /**
     * A method adds element to the tree
     * @param element element to add
     * @return true if adding was successful, and false otherwise
     * */
    public boolean add(E element) {
        if (root != null) {
            if (!root.addElement(element)) {
                return false;
            }
            root = root.balance();
        } else {
            root = new Node(element, null, null, 1);
        }
        size++;
        return true;
    }

    /**
     * A method removes element from the tree
     * @param element element to remove
     * @return true if removing was successful, and false otherwise
     * */
    public boolean remove(E element) {
        if (contains(element)) {
            root = root.removeElement(element);
            size--;
            return true;
        }
        return false;
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

        /**A method returns height of received node*/
        private int height(Node node) {
            return node == null ? 0 : node.height;
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

        /**
         * An auxiliary method adding received element to the tree, root of which is this node
         * Returns true if adding was successful and false otherwise
         * */
        private boolean addElement(E value) {
            if (value.equals(this.value)) {
                return false;
            }

            if (((Comparable)value).compareTo(this.value) < 0) {
                if (this.leftChild != null) {
                    if (!this.leftChild.addElement(value)) {
                        return false;
                    }
                    this.leftChild = this.leftChild.balance();
                } else {
                    this.leftChild = new Node(value, null, null, 1);
                }
            } else {
                if (this.rightChild != null) {
                    if (!this.rightChild.addElement(value)) {
                        return false;
                    }
                    this.rightChild = this.rightChild.balance();
                } else {
                    this.rightChild = new Node(value, null, null, 1);
                }
            }
            return true;
        }

        /**An auxiliary method removes this node and returns tree without it*/
        private Node removeElement() {
            if (this.leftChild != null) {
                if (this.rightChild != null) {
                    Node current = this.rightChild;

                    while (current.leftChild != null) {
                        current = current.leftChild;
                    }

                    //swap
                    E value = this.value;
                    this.value = current.value;
                    current.value = value;

                    this.rightChild = this.rightChild.removeElement(current.value);
                    return this.balance();
                } else {
                    return this.leftChild;
                }
            } else {
                return this.rightChild;
            }
        }

        /**
         * An auxiliary method removes received value from the tree, root of which is this node,
         * and returns tree without this value
         * */
        private Node removeElement(Object value) {
            if (value.equals(this.value)) {
                return this.removeElement();
            }

            if (((Comparable)value).compareTo(this.value) < 0) {
                if (this.leftChild != null) {
                    this.leftChild = this.leftChild.removeElement(value);
                    return this.balance();
                }
            } else {
                if (this.rightChild != null) {
                    this.rightChild = this.rightChild.removeElement(value);
                    return this.balance();
                }
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

        /**A method returns true if removing was successful, and false otherwise*/
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
