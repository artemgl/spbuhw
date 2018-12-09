#pragma once
#include "string.h"
#include "tree.h"

struct Bucket
{
    char symbol;
    String *code;
};

struct HashTable
{
    Bucket **buckets;
    int size;
    int capacity;
};

HashTable *createHashTable();
void deleteHashTable(HashTable *table);

double loadFactor(HashTable *table);

int hash(char symbol, int modulo);

void expandHashTable(HashTable *&table);
void addElement(HashTable *&table, char symbol, String *code);

String *returnString(HashTable *table, char symbol);

HashTable *buildHashTable(Tree *tree);

void encryptInFile(HashTable *table, const char fileForReadingName[], const char fileForWritingName[]);

void printHashTable(HashTable *table);
