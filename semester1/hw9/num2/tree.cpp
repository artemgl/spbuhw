#include <iostream>
#include <fstream>
#include "tree.h"
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

void addElement(Tree *tree, char symbol)
{
    tree->root = new Node {symbol, nullptr, nullptr};
}

void printTree(Node *node)
{
    if (isLeaf(node))
    {
        cout << (char)node->symbol;
        return;
    }

    cout << "(0 ";
    printTree(node->leftChild);
    cout << ' ';
    printTree(node->rightChild);
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

void printTreeInFile(Node *node, ofstream &fout)
{
    if (isLeaf(node))
    {
        fout << (char)node->symbol;
        return;
    }

    fout << "(0 ";
    printTreeInFile(node->leftChild, fout);
    fout << ' ';
    printTreeInFile(node->rightChild, fout);
    fout << ')';
}

void printTreeInFile(Tree *tree, const char fileName[])
{
    ofstream fout(fileName);

    if (tree->root)
    {
        printTreeInFile(tree->root, fout);
    }
    else
    {
        fout << "(null)";
    }

    fout << endl;

    fout.close();
}

bool isLeaf(Node *node)
{
    return !node->leftChild && !node->rightChild;
}

bool exists(Node *node, char symbol)
{
    if (node->symbol != symbol)
    {
        if (isLeaf(node))
        {
            return false;
        }

        return exists(node->leftChild, symbol) || exists(node->rightChild, symbol);
    }

    return true;
}

bool exists(Tree *tree, char symbol)
{
    return exists(tree->root, symbol);
}
