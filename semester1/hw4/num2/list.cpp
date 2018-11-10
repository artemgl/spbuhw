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
        ListElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }

    delete list;
}

void addElement(List *list, char name[maxLength], char number[maxLength])
{
    if (list->first)
    {
        ListElement *current = list->first;
        while (current->next)
        {
            current = current->next;
        }

        current->next = new ListElement {};

        for (int i = 0; i < maxLength; i++)
        {
            current->next->name[i] = name[i];
            current->next->number[i] = number[i];
        }
        current->next->next = nullptr;
    }
    else
    {
        list->first = new ListElement {};

        for (int i = 0; i < maxLength; i++)
        {
            list->first->name[i] = name[i];
            list->first->number[i] = number[i];
        }
        list->first->next = nullptr;
    }
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->name << ' ' << current->number << endl;
        current = current->next;
    }
}
