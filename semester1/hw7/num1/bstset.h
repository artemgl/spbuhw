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

void addElement(Tree *tree, int value);
void removeElement(Tree *tree, int value);

bool belongs(Tree *tree, int value);

void printTreeAscending(Tree *tree);
void printTreeDescending(Tree *tree);
void printTree(Tree *tree);
