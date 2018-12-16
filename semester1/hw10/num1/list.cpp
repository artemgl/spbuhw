#include <iostream>
#include "list.h"
using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    if (!list)
    {
        return;
    }

    ListElement *current = list->first;
    while (current)
    {
        list->first = current->next;
        delete current;
        current = list->first;
    }

    delete list;
}

void addElement(List *list, int top)
{
    if (exists(list, top))
    {
        return;
    }

    list->first = new ListElement {top, list->first};
}

void removeElement(ListElement *&listElement, int top)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->top == top)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, top);
}

void removeElement(List *list, int top)
{
    removeElement(list->first, top);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->top << ' ';
        current = current->next;
    }
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int top)
{
    ListElement *current = list->first;

    while (current)
    {
        if (current->top == top)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}
