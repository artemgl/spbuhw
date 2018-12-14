#pragma once

struct ListElement
{
    int number;
    int amount;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int number);
void removeElement(List *list, int number);
void printList(List *list);

bool isEmpty(List *list);
bool exists(List *list, int number);
