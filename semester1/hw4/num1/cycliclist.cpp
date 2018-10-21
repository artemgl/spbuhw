#include <iostream>
#include "cycliclist.h"

CyclicList *createCyclicList()
{
    return new CyclicList {nullptr};
}

void deleteCyclicList(CyclicList *list)
{
    if (list->first)
    {
        CyclicListElement *current = list->first;
        while (current->next != list->first)
        {
            CyclicListElement *nextElement = current->next;
            delete current;
            current = nextElement;
        }
        delete current;
    }

    delete list;
}

void addToEnd(CyclicList *list, int value)
{
    if (list->first == nullptr)
    {
        list->first = new CyclicListElement {value, nullptr};
        list->first->next = list->first;
        return;
    }
    CyclicListElement *lastElement = list->first;
    while (lastElement->next != list->first)
    {
        lastElement = lastElement->next;
    }

    lastElement->next = new CyclicListElement {value, list->first};
}

void removeElement(CyclicList *list, int index)
{
    if (index == 1)
    {
        list->first = list->first->next;
        index = size(list);
    }

    CyclicListElement *current = list->first;
    while (current->next != list->first)
    {
        current = current->next;
    }

    for (int i = 0; i < index - 1; i++)
    {
        current = current->next;
    }

    CyclicListElement *buffer = current->next->next;
    delete current->next;
    current->next = buffer;
}

int size(CyclicList *list)
{
    if (list->first == nullptr)
    {
        return 0;
    }
    CyclicListElement *current = list->first;
    int size = 1;
    while (current->next != list->first)
    {
        size++;
        current = current->next;
    }

    return size;
}

int returnElement(CyclicList *list, int index)
{
    CyclicListElement *current = list->first;
    while (current->next != list->first)
    {
        current = current->next;
    }

    for (int i = 0; i < index; i++)
    {
        current = current->next;
    }

    return current->number;
}
