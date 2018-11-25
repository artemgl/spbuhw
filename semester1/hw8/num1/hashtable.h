#pragma once
#include "list.h"

struct HashTable
{
    List **buckets;
    int size;
    int capacity;
};

HashTable *createHashTable();
void deleteHashTable(HashTable *table);

double loadFactor(HashTable *table);

int hashInt(int number);

void addElement(HashTable *table, int firstNumber, int secondNumber);
void removeElement(HashTable *table, int firstNumber, int secondNumber);

int returnSecondNumber(HashTable *table, int firstNumber);
