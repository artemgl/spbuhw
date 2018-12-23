#pragma once

struct Node
{
    char symbol;
    Node *leftChild;
    Node *rightChild;
};

struct Tree
{
    Node *root;
};

Tree *createTree();
void deleteTree(Tree *tree);

void addElement(Tree *tree, char symbol);

bool exists(Tree *tree, char symbol);
bool isLeaf(Node *node);

void printTree(Tree *tree);
void printTreeInFile(Tree *tree, const char fileName[]);
