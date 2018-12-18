#pragma once

int const maxLength = 256;

struct ListElement;
struct List;

List *createList();
void deleteList(List *list);

void addElement(List *list, char name[], char number[]);
void printList(List *list);
