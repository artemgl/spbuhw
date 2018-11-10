#pragma once

struct Node
{
    int value;
    int height;
    Node *leftChild;
    Node *rightChild;
};

struct Tree
{
    Node *root;
};

Tree *createTree();
void deleteTree(Tree *tree);

int height(Node *node);
int balanceFactor(Node *node);
void updateHeight(Node *node);

Node *rotateRight(Node *node);
Node *rotateLeft(Node *node);
Node *balance(Node *node);

void addElement(Tree *tree, int value);
void removeElement(Tree *tree, int value);

bool belongs(Tree *tree, int value);

void printTreeAscending(Tree *tree);
void printTreeDescending(Tree *tree);
void printTree(Tree *tree);
