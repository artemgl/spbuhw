#include <iostream>
#include <fstream>
#include <string.h>
#include "ast.h"
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

bool isLeaf(Node *node)
{
    return !(node->leftChild);
}

int calculate(Node *node)
{
    int leftValue = 0;
    int rightValue = 0;

    if (isLeaf(node->leftChild))
    {
        leftValue = node->leftChild->value;
    }
    else
    {
        leftValue = calculate(node->leftChild);
    }

    if (isLeaf(node->rightChild))
    {
        rightValue = node->rightChild->value;
    }
    else
    {
        rightValue = calculate(node->rightChild);
    }

    switch ((char)node->value)
    {
    case '+':
    {
        return leftValue + rightValue;
        break;
    }
    case '-':
    {
        return leftValue - rightValue;
        break;
    }
    case '*':
    {
        return leftValue * rightValue;
        break;
    }
    case '/':
    {
        return leftValue / rightValue;
        break;
    }
    }

    return 0;
}

int calculate(Tree *tree)
{
    if (tree->root)
    {
        return calculate(tree->root);
    }

    return 0;
}

void readOperand(Node *&node, ifstream &fin);

void readTree(Node *node, ifstream &fin)
{
    char currentSymbol = '\0';

    fin.get(currentSymbol);
    node->value = currentSymbol;

    fin.get();

    readOperand(node->leftChild, fin);
    readOperand(node->rightChild, fin);

    fin.get();
}

void readOperand(Node *&node, ifstream &fin)
{
    int const maxLength = 10;

    char currentSymbol = '\0';
    fin.get(currentSymbol);
    if (isdigit(currentSymbol))
    {
        char number[maxLength] {};
        for (int i = 0; isdigit(currentSymbol); i++)
        {
            number[i] = currentSymbol;
            fin.get(currentSymbol);
        }

        int result = 0;
        for (unsigned int i = 0; i < strlen(number); i++)
        {
            result = result * 10 + (number[i] - '0');
        }

        node = new Node {result, nullptr, nullptr};
    }
    else
    {
        node = new Node {0, nullptr, nullptr};
        readTree(node, fin);
    }
}

void copyTreeFromFile(Tree *tree, char const fileName[])
{
    ifstream fin(fileName);

    fin.get();
    tree->root = new Node {0, nullptr, nullptr};
    readTree(tree->root, fin);

    fin.close();
}

void printTree(Node *node)
{
    cout << '(';

    if (isLeaf(node->leftChild))
    {
        cout << node->leftChild->value;
    }
    else
    {
        printTree(node->leftChild);
    }

    cout << ' ' << (char)node->value << ' ';

    if (isLeaf(node->rightChild))
    {
        cout << node->rightChild->value;
    }
    else
    {
        printTree(node->rightChild);
    }

    cout << ')';
}

void printTree(Tree *tree)
{
    if (tree->root)
    {
        printTree(tree->root);
    }

    cout << endl;
}
