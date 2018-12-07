#pragma once

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int value);
void removeElement(List *list, int value);
void printList(List *list);

bool isEmpty(List *list);
bool exists(List *list, int value);

void sortList(List *list);
