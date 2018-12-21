#pragma once

struct ListElement
{
    int top;
    int distance;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int top, int distance);
void removeElement(List *list, int top, int distance);
void printList(List *list);

bool isEmpty(List *list);
bool exists(List *list, int top, int distance);
