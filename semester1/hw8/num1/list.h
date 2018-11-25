#pragma once

struct ListElement
{
    int firstNumber;
    int secondNumber;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList();
void deleteList(List *list);

void addElement(List *list, int firstNumber, int secondNumber);
void removeElement(List *list, int firstNumber, int secondNumber);
void printList(List *list);

bool isEmpty(List *list);
bool exists(List *list, int firstNumber, int secondNumber);
