#include <iostream>
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

void addElement(List *list, int top, int distance)
{
    if (exists(list, top, distance))
    {
        return;
    }

    list->first = new ListElement {top, distance, list->first};

    ListElement *current = list->first;

    while (current->next && current->distance > current->next->distance)
    {
        swap(current->distance, current->next->distance);
        swap(current->top, current->next->top);
        current = current->next;
    }
}

void removeElement(ListElement *&listElement, int top, int distance)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->top == top && listElement->distance == distance)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, top, distance);
}

void removeElement(List *list, int top, int distance)
{
    removeElement(list->first, top, distance);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->top << ':' << current->distance << ' ';
        current = current->next;
    }
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int top, int distance)
{
    ListElement *current = list->first;

    while (current)
    {
        if (current->top == top && current->distance == distance)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}
