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

void addElement(List *list, int number)
{
    if (isEmpty(list))
    {
        list->first = new ListElement {number, 1, list->first};
        return;
    }

    ListElement *current = list->first;

    if (exists(list, number))
    {
        while (current->number != number)
        {
            current = current->next;
        }

        current->amount++;

        return;
    }

    list->first = new ListElement {number, 1, list->first};

    while (current->next && current->number > current->next->number)
    {
        swap(current->amount, current->next->amount);
        swap(current->number, current->next->number);
        current = current->next;
    }
}

void removeElement(ListElement *&listElement, int number)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->number == number)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, number);
}

void removeElement(List *list, int number)
{
    removeElement(list->first, number);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->number << ": " << current->amount << " times" << endl;
        current = current->next;
    }
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int number)
{
    if (isEmpty(list))
    {
        return false;
    }

    ListElement *current = list->first;

    while (current)
    {
        if (current->number == number)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}
