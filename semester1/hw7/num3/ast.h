#pragma once

struct Node
{
    int value;
    Node *leftChild;
    Node *rightChild;
};

struct Tree
{
    Node *root;
};

Tree *createTree();
void deleteTree(Tree *tree);

bool isLeaf(Node *node);

int calculate(Tree *tree);

void copyTreeFromFile(Tree *tree, char const fileName[]);

void printTree(Tree *tree);
