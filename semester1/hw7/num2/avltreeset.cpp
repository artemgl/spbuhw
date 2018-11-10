#include <iostream>
#include "avltreeset.h"
using namespace std;

Tree *createTree()
{
    return new Tree {nullptr};
}

void deleteTree(Node *node)
{
    if (node->leftChild)
    {
        deleteTree(node->leftChild);
    }
    if (node->rightChild)
    {
        deleteTree(node->rightChild);
    }
    delete node;
}

void deleteTree(Tree *tree)
{
    if (tree->root)
    {
        deleteTree(tree->root);
    }
    delete tree;
}

int height(Node *node)
{
    return node ? node->height : 0;
}

int balanceFactor(Node *node)
{
    return height(node->rightChild) - height(node->leftChild);
}

void updateHeight(Node *node)
{
    int leftHeight = height(node->leftChild);
    int rightHeight = height(node->rightChild);
    node->height = ((rightHeight > leftHeight) ? rightHeight : leftHeight) + 1;
}

Node *rotateRight(Node *node)
{
    Node *left = node->leftChild;
    node->leftChild = left->rightChild;
    left->rightChild = node;

    updateHeight(node);
    updateHeight(left);

    return left;
}

Node *rotateLeft(Node *node)
{
    Node *right = node->rightChild;
    node->rightChild = right->leftChild;
    right->leftChild = node;

    updateHeight(node);
    updateHeight(right);

    return right;
}

Node *balance(Node *node)
{
    updateHeight(node);

    switch (balanceFactor(node))
    {
    case 2:
    {
        if (balanceFactor(node->rightChild) == -1)
        {
            node->rightChild = rotateRight(node->rightChild);
        }
        return rotateLeft(node);
        break;
    }
    case -2:
    {
        if (balanceFactor(node->leftChild) == 1)
        {
            node->leftChild = rotateLeft(node->leftChild);
        }
        return rotateRight(node);
        break;
    }
    }

    return node;
}

void addElement(Node *node, int value)
{
    if (value == node->value)
    {
        return;
    }

    if (value < node->value)
    {
        if (node->leftChild)
        {
            addElement(node->leftChild, value);
            node->leftChild = balance(node->leftChild);
        }
        else
        {
            node->leftChild = new Node {value, 1, nullptr, nullptr};
        }
    }
    else
    {
        if (node->rightChild)
        {
            addElement(node->rightChild, value);
            node->rightChild = balance(node->rightChild);
        }
        else
        {
            node->rightChild = new Node {value, 1, nullptr, nullptr};
        }
    }
}

void addElement(Tree *tree, int value)
{
    if (tree->root)
    {
        addElement(tree->root, value);
        tree->root = balance(tree->root);
    }
    else
    {
        tree->root = new Node {value, 1, nullptr, nullptr};
    }
}

void removeElement(Node *&node, int value)
{
    if (value == node->value)
    {
        if (node->leftChild)
        {
            if (node->rightChild)
            {
                Node *current = node->rightChild;
                Node *previous = node;
                while (current->leftChild)
                {
                    previous = current;
                    current = current->leftChild;
                }

                swap(node->value, current->value);

                if (previous != node)
                {
                    delete previous->leftChild;
                    previous->leftChild = nullptr;
                }
                else
                {
                    delete previous->rightChild;
                    previous->rightChild = nullptr;
                }
            }
            else
            {
                Node *left = node->leftChild;
                delete node;
                node = left;
            }
        }
        else
        {
            if (node->rightChild)
            {
                Node *right = node->rightChild;
                delete node;
                node = right;
            }
            else
            {
                delete node;
                node = nullptr;
            }
        }

        return;
    }

    if (value < node->value)
    {
        if (node->leftChild)
        {
            removeElement(node->leftChild, value);
            node = balance(node);
        }
    }
    else
    {
        if (node->rightChild)
        {
            removeElement(node->rightChild, value);
            node = balance(node);
        }
    }
}

void removeElement(Tree *tree, int value)
{
    if (tree->root)
    {
        removeElement(tree->root, value);
    }
}

bool belongs(Tree *tree, int value)
{
    Node *current = tree->root;
    while (current)
    {
        if (value == current->value)
        {
            break;
        }

        if (value < current->value)
        {
            current = current->leftChild;
        }
        else
        {
            current = current->rightChild;
        }
    }

    return current;
}

void printTreeAscending(Node *node)
{
    if (node->leftChild)
    {
        printTreeAscending(node->leftChild);
    }

    cout << ' ' << node->value << ' ';

    if (node->rightChild)
    {
        printTreeAscending(node->rightChild);
    }
}

void printTreeAscending(Tree *tree)
{
    if (tree->root)
    {
        printTreeAscending(tree->root);
    }

    cout << endl;
}

void printTreeDescending(Node *node)
{
    if (node->rightChild)
    {
        printTreeDescending(node->rightChild);
    }

    cout << ' ' << node->value << ' ';

    if (node->leftChild)
    {
        printTreeDescending(node->leftChild);
    }
}

void printTreeDescending(Tree *tree)
{
    if (tree->root)
    {
        printTreeDescending(tree->root);
    }

    cout << endl;
}

void printTree(Node *node)
{
    cout << '(' << node->value << ' ';

    if (node->leftChild)
    {
        printTree(node->leftChild);
    }
    else
    {
        cout << "null";
    }

    cout << ' ';

    if (node->rightChild)
    {
        printTree(node->rightChild);
    }
    else
    {
        cout << "null";
    }

    cout << ')';
}

void printTree(Tree *tree)
{
    if (tree->root)
    {
        printTree(tree->root);
    }
    else
    {
        cout << "(null)";
    }

    cout << endl;
}
