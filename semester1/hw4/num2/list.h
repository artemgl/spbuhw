#pragma once

int const maxLength = 256;

struct ListElement
{
    char name[maxLength];
    char number[maxLength];
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, char name[], char number[]);
void printList(List *list);
