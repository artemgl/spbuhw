#pragma once
#include "tree.h"

struct PriorityQueueElement
{
    int priority;
    Tree *tree;
    PriorityQueueElement *next;
};

struct PriotityQueue
{
    PriorityQueueElement *first;
};

PriotityQueue *createPriorityQueue();
void deletePriorityQueue(PriotityQueue *priorityQueue);

void addElement(PriotityQueue *priorityQueue, char symbol);
void removeElement(PriotityQueue *priorityQueue, char symbol);

void printPriorityQueue(PriotityQueue *priorityQueue);

Tree *buildTree(PriotityQueue *priorityQueue);

bool isEmpty(PriotityQueue *priorityQueue);
bool exists(PriotityQueue *priorityQueue, char symbol);
