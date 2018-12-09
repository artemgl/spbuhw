#include <iostream>
#include "list.h"
#include "tree.h"
using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        list->first = current->next;
        deleteTree(current->tree);
        delete current;
        current = list->first;
    }

    delete list;
}

void addElement(List *list, char symbol)
{
    if (exists(list, symbol))
    {
        ListElement *current = list->first;
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

    list->first = new ListElement {1, newTree, list->first};
}

Tree *buildTree(List *list)
{
    while (list->first->next)
    {
        ListElement *first = list->first;
        ListElement *second = first->next;

        second->priority += first->priority;

        second->tree->root = new Node {'\0', first->tree->root, second->tree->root};
        delete first->tree;
        delete first;
        list->first = second;

        ListElement *current = list->first;
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

    Tree *newTree = list->first->tree;
    delete list->first;
    delete list;
    return newTree;
}

void printList(List *list)
{
    ListElement *current = list->first;

    while (current)
    {
        cout << current->priority << ' ';
        printTree(current->tree);

        current = current->next;
    }
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, char symbol)
{
    ListElement *current = list->first;

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
