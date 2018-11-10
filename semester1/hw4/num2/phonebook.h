#pragma once

void addNote(List *list, char name[], char number[]);

void printName(List *list, char number[]);
void printNumber(List *list, char name[]);

void copyFromFile(List *list, char const fileName[]);
void saveInFile(List *list, char const fileName[]);
