#pragma once
#include "tree.h"

struct ListElement
{
    int priority;
    Tree *tree;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, char symbol);
void removeElement(List *list, char symbol);

void printList(List *list);

Tree *buildTree(List *list);

bool isEmpty(List *list);
bool exists(List *list, char symbol);
