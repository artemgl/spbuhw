#pragma once

struct ListElement
{
    int top;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int top);
void removeElement(List *list, int top);
void printList(List *list);

bool isEmpty(List *list);
bool exists(List *list, int top);
