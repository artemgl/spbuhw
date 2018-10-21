#pragma once

struct CyclicListElement
{
    int number;
    CyclicListElement *next;
};

struct CyclicList
{
    CyclicListElement *first;
};

CyclicList *createCyclicList();
void deleteCyclicList(CyclicList *list);

void addToEnd(CyclicList *list, int value);
void removeElement(CyclicList *list, int index);

int size(CyclicList *list);
int returnElement(CyclicList *list, int index);
