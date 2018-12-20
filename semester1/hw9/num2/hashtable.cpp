#include <iostream>
#include <fstream>
#include "hashtable.h"
#include "string.h"

HashTable *createHashTable(int capacity)
{
    HashTable *table = new HashTable {};

    table->capacity = capacity;
    table->size = 0;
    table->buckets = new Bucket *[capacity] {};

    return table;
}

HashTable *createHashTable()
{
    return createHashTable(8);
}

void deleteHashTable(HashTable *table)
{
    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            deleteString(table->buckets[i]->code);
            delete table->buckets[i];
        }
    }

    delete[] table->buckets;
    delete table;
}

double loadFactor(HashTable *table)
{
    return (double)table->size / table->capacity;
}

int hash(char symbol, int modulo)
{
    int result = (int)symbol;

    result ^= (result << 13);
    result ^= (result >> 17);
    result ^= (result << 5);

    return result % modulo;
}

void expandHashTable(HashTable *&table)
{
    HashTable *newTable = createHashTable(2 * table->capacity);

    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            addElement(newTable, table->buckets[i]->symbol, table->buckets[i]->code);
        }
    }

    deleteHashTable(table);
    table = newTable;
}

void addElement(HashTable *&table, char symbol, String *code)
{
    int newIndex = hash(symbol, table->capacity);
    int amountOfAttempts = 0;
    while (table->buckets[newIndex])
    {
        if (isEqual(table->buckets[newIndex]->code, code))
        {
            return;
        }
        newIndex = (newIndex + ++amountOfAttempts) % table->capacity;
    }

    table->buckets[newIndex] = new Bucket {symbol, clone(code)};
    table->size++;

    if (table->size == table->capacity)
    {
        expandHashTable(table);
    }
}

void buildHashTable(HashTable *&table, Node *node, String *code)
{
    if (isLeaf(node))
    {
        addElement(table, node->symbol, code);
        return;
    }

    String *extraBit = createString("0");
    String *newCode = clone(code);
    concate(newCode, extraBit);
    deleteString(extraBit);
    buildHashTable(table, node->leftChild, newCode);
    deleteString(newCode);

    extraBit = createString("1");
    newCode = clone(code);
    concate(newCode, extraBit);
    deleteString(extraBit);
    buildHashTable(table, node->rightChild, newCode);
    deleteString(newCode);
}

HashTable *buildHashTable(Tree *tree)
{
    HashTable *newTable = createHashTable();

    if (tree->root)
    {
        if (isLeaf(tree->root))
        {
            String *code = createString("0");
            addElement(newTable, tree->root->symbol, code);
            deleteString(code);
            return newTable;
        }

        String *code = createString("");
        buildHashTable(newTable, tree->root, code);
    }

    return newTable;
}

String *returnString(HashTable *table, char symbol)
{
    int amountOfAttempts = 0;
    int newIndex = hash(symbol, table->capacity);
    do
    {
        newIndex = (newIndex + amountOfAttempts++) % table->capacity;
        if (!table->buckets[newIndex])
        {
            return nullptr;
        }
    }
    while (table->buckets[newIndex]->symbol != symbol);

    return table->buckets[newIndex]->code;
}

void encryptInFile(HashTable *table, const char fileForReadingName[], const char fileForWritingName[])
{
    std::ifstream fin(fileForReadingName);

    char symbol = '\0';
    fin.get(symbol);

    while (!fin.eof())
    {
        printStringInFile(returnString(table, symbol), fileForWritingName);
        fin.get(symbol);
    }

    fin.close();
}

void printHashTable(HashTable *table)
{
    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            std::cout << table->buckets[i]->symbol << ' ';
            printString(table->buckets[i]->code);
            std::cout << std::endl;
        }
        else
        {
            std::cout << '-' << std::endl;
        }
    }
}
