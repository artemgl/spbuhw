#include <iostream>
#include <fstream>
#include "list.h"
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
        delete current;
        current = list->first;
    }

    delete list;
}

void addElement(ListElement *&current, int value)
{
    if (current)
    {
        addElement(current->next, value);
        return;
    }

    current = new ListElement {value, nullptr};
}

void addElement(List *list, int value)
{
    addElement(list->first, value);
}

void removeElement(ListElement *&listElement, int value)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->value == value)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, value);
}

void removeElement(List *list, int value)
{
    removeElement(list->first, value);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->value << ' ';
        current = current->next;
    }
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int value)
{
    ListElement *current = list->first;

    while (current)
    {
        if (current->value == value)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}

ListElement *previousElement(List *list, ListElement *currentElement)
{
    if (list->first == currentElement)
    {
        return nullptr;
    }

    ListElement *previous = list->first;

    while (previous->next != currentElement)
    {
        previous = previous->next;
    }

    return previous;
}

void sortList(List *list)
{
    ListElement *main = list->first;

    while (main->next)
    {
        main = main->next;
    }

    while (main)
    {
        ListElement *current = main;

        while (current->next)
        {
            if (current->value > current->next->value)
            {
                swap(current->value, current->next->value);
            }

            current = current->next;
        }

        main = previousElement(list, main);
    }
}
