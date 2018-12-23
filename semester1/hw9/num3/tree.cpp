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

Node *buildTreeFromFile(ifstream &fin)
{
    Tree *newTree = createTree();
    addElement(newTree, '\0');

    fin.get();
    fin.get();

    char symbol = '\0';
    fin.get(symbol);

    if (symbol == '(')
    {
        newTree->root->leftChild = buildTreeFromFile(fin);
    }
    else
    {
        newTree->root->leftChild = new Node {symbol, nullptr, nullptr};
    }

    fin.get();
    fin.get(symbol);

    if (symbol == '(')
    {
        newTree->root->rightChild = buildTreeFromFile(fin);
    }
    else
    {
        newTree->root->rightChild = new Node {symbol, nullptr, nullptr};
    }

    fin.get();

    Node *result = newTree->root;
    delete newTree;
    return result;
}

Tree *buildTreeFromFile(const char fileName[])
{
    ifstream fin(fileName);

    Tree *newTree = createTree();

    char symbol = '\0';
    fin.get(symbol);

    if (symbol != '(')
    {
        addElement(newTree, '\0');
        newTree->root->leftChild = new Node {symbol, nullptr, nullptr};
        return newTree;
    }

    newTree->root = buildTreeFromFile(fin);

    fin.close();

    return newTree;
}

void decipherInFile(Tree *tree, const char fileForReadingName[], const char fileForWritingName[])
{
    ifstream fin(fileForReadingName);

    char symbol = '\0';
    do
    {
        do
        {
            fin.get(symbol);
        }
        while (symbol != '\n');
        fin.get(symbol);
    }
    while (symbol != '0' && symbol != '1');

    Node *current = tree->root;

    ofstream fout(fileForWritingName);

    while (!fin.eof())
    {
        if (symbol == '0')
        {
            current = current->leftChild;
        }
        else
        {
            current = current->rightChild;
        }

        if (isLeaf(current))
        {
            fout << current->symbol;
            current = tree->root;
        }

        fin.get(symbol);
    }

    fout.close();
    fin.close();
}
