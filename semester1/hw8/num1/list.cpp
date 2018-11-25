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

void addElement(List *list, int firstNumber, int secondNumber)
{
    list->first = new ListElement {firstNumber, secondNumber, list->first};
}

void removeElement(ListElement *&listElement, int firstNumber, int secondNumber)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->firstNumber == firstNumber && listElement->secondNumber == secondNumber)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, firstNumber, secondNumber);
}

void removeElement(List *list, int firstNumber, int secondNumber)
{
    removeElement(list->first, firstNumber, secondNumber);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->firstNumber << ' ' << current->secondNumber << endl;
        current = current->next;
    }

    cout << endl;
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int firstNumber, int secondNumber)
{
    ListElement *current = list->first;

    while (current)
    {
        if (current->firstNumber == firstNumber && current->secondNumber == secondNumber)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}
