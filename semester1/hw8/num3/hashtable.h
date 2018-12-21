#pragma once
#include "string.h"

struct Bucket
{
    String *word;
    int amountOfWords;
    int amountOfAttempts;
};

struct HashTable
{
    Bucket **buckets;
    int size;
    int capacity;
};

HashTable *createHashTable();
void deleteHashTable(HashTable *table);

bool existsString(HashTable *table, String *string);

double loadFactor(HashTable *table);

int hash(String *string, int modulo);

void expandHashTable(HashTable *&table);
void addElement(HashTable *&table, String *string);

int averageAmountOfAttempts(HashTable *table);
int maxAmountOfAttempts(HashTable *table);
int amountOfWords(HashTable *table);
int amountOfEmptyBuckets(HashTable *table);

void printWordsWithMaxAmountOfAttempts(HashTable *table);
void printHashTable(HashTable *table);
