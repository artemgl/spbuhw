#include <iostream>
#include "bstset.h"
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
        }
        else
        {
            node->leftChild = new Node {value, nullptr, nullptr};
        }
    }
    else
    {
        if (node->rightChild)
        {
            addElement(node->rightChild, value);
        }
        else
        {
            node->rightChild = new Node {value, nullptr, nullptr};
        }
    }
}

void addElement(Tree *tree, int value)
{
    if (tree->root)
    {
        addElement(tree->root, value);
    }
    else
    {
        tree->root = new Node {value, nullptr, nullptr};
    }
}

void removeElement(Node *&node, int value);

void removeElement(Node *&node)
{
    if (node->leftChild)
    {
        if (node->rightChild)
        {
            Node *current = node->rightChild;

            while (current->leftChild)
            {
                current = current->leftChild;
            }

            swap(node->value, current->value);

            removeElement(node->rightChild, current->value);
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
}

void removeElement(Node *&node, int value)
{
    if (value == node->value)
    {
        removeElement(node);

        return;
    }

    if (value < node->value)
    {
        if (node->leftChild)
        {
            removeElement(node->leftChild, value);
        }
    }
    else
    {
        if (node->rightChild)
        {
            removeElement(node->rightChild, value);
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
