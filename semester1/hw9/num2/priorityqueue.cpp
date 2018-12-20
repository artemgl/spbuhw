#include <iostream>
#include "priorityqueue.h"
#include "tree.h"
using namespace std;

PriotityQueue *createPriorityQueue()
{
    return new PriotityQueue {nullptr};
}

void deletePriorityQueue(PriotityQueue *priorityQueue)
{
    PriorityQueueElement *current = priorityQueue->first;
    while (current)
    {
        priorityQueue->first = current->next;
        deleteTree(current->tree);
        delete current;
        current = priorityQueue->first;
    }

    delete priorityQueue;
}

void addElement(PriotityQueue *priorityQueue, char symbol)
{
    if (exists(priorityQueue, symbol))
    {
        PriorityQueueElement *current = priorityQueue->first;
        while (!exists(current->tree, symbol))
        {
            current = current->next;
        }

        current->priority++;

        while (current->next)
        {
            if (current->priority > current->next->priority)
            {
                swap(current->priority, current->next->priority);
                swap(current->tree, current->next->tree);
            }
            else
            {
                break;
            }

            current = current->next;
        }

        return;
    }

    Tree *newTree = createTree();
    addElement(newTree, symbol);

    priorityQueue->first = new PriorityQueueElement {1, newTree, priorityQueue->first};
}

Tree *buildTree(PriotityQueue *priorityQueue)
{
    while (priorityQueue->first->next)
    {
        PriorityQueueElement *first = priorityQueue->first;
        PriorityQueueElement *second = first->next;

        second->priority += first->priority;

        second->tree->root = new Node {'\0', first->tree->root, second->tree->root};
        delete first->tree;
        delete first;
        priorityQueue->first = second;

        PriorityQueueElement *current = priorityQueue->first;
        while (current->next)
        {
            if (current->priority > current->next->priority)
            {
                swap(current->priority, current->next->priority);
                swap(current->tree, current->next->tree);
            }
            else
            {
                break;
            }

            current = current->next;
        }
    }

    Tree *newTree = priorityQueue->first->tree;
    delete priorityQueue->first;
    delete priorityQueue;
    return newTree;
}

void printPriorityQueue(PriotityQueue *priorityQueue)
{
    PriorityQueueElement *current = priorityQueue->first;

    while (current)
    {
        cout << current->priority << ' ';
        printTree(current->tree);

        current = current->next;
    }
}

bool isEmpty(PriotityQueue *priorityQueue)
{
    return !(priorityQueue->first);
}

bool exists(PriotityQueue *priorityQueue, char symbol)
{
    PriorityQueueElement *current = priorityQueue->first;

    while (current)
    {
        if (exists(current->tree, symbol))
        {
            return true;
        }

        current = current->next;
    }

    return false;
}
