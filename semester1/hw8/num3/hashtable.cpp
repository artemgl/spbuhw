#include <iostream>
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
    return createHashTable(1024);
}

void deleteHashTable(HashTable *table)
{
    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            deleteString(table->buckets[i]->word);
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

int hash(String *string, int modulo)
{
    int p = 13;
    int lengthOfString = length(string);

    int result = 0;
    for (int i = 0; i < lengthOfString; i++)
    {
        result = ((result * p) % modulo + string->symbols[i]) % modulo;
    }

    return result;
}

void expandHashTable(HashTable *&table)
{
    HashTable *newTable = createHashTable(2 * table->capacity);

    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            for (int j = 0; j < table->buckets[i]->amountOfWords; j++)
            {
                addElement(newTable, table->buckets[i]->word);
            }
        }
    }

    deleteHashTable(table);
    table = newTable;
}

void addElement(HashTable *&table, String *string)
{
    int newIndex = hash(string, table->capacity);
    int amountOfAttempts = 0;
    int step = 0;
    while (table->buckets[newIndex])
    {
        if (isEqual(table->buckets[newIndex]->word, string))
        {
            table->buckets[newIndex]->amountOfWords++;
            return;
        }
        step += 5;
        amountOfAttempts++;
        newIndex = (newIndex + step) % table->capacity;
    }

    table->buckets[newIndex] = new Bucket {clone(string), 1, amountOfAttempts + 1};
    table->size++;

    if (table->size == table->capacity)
    {
        expandHashTable(table);
    }
}

int averageAmountOfAttempts(HashTable *table)
{
    int result = 0;

    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            result += table->buckets[i]->amountOfAttempts;
        }
    }

    return (double)result / table->size;
}

int maxAmountOfAttempts(HashTable *table)
{
    int result = 0;

    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i] && result < table->buckets[i]->amountOfAttempts)
        {
            result = table->buckets[i]->amountOfAttempts;
        }
    }

    return result;
}

int amountOfWords(HashTable *table)
{
    int result = 0;

    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            result += table->buckets[i]->amountOfWords;
        }
    }

    return result;
}

int amountOfEmptyBuckets(HashTable *table)
{
    return table->capacity - table->size;
}

void printChainOfWords(HashTable *table, String *string)
{
    int step = 0;
    int newIndex = hash(string, table->capacity);
    do
    {
        newIndex = (newIndex + step) % table->capacity;
        step += 5;

        printString(table->buckets[newIndex]->word);
        std::cout << std::endl;
    }
    while (!isEqual(table->buckets[newIndex]->word, string));
}

void printWordsWithMaxAmountOfAttempts(HashTable *table)
{
    int maxAmount = maxAmountOfAttempts(table);

    for (int i = 0; i < table->capacity; i++)
    {
        if ((table->buckets[i]) && (table->buckets[i]->amountOfAttempts == maxAmount))
        {
            printChainOfWords(table, table->buckets[i]->word);
            std::cout << std::endl;
        }
    }
}

void printHashTable(HashTable *table)
{
    for (int i = 0; i < table->capacity; i++)
    {
        if (table->buckets[i])
        {
            printString(table->buckets[i]->word);
            std::cout << ' ' << table->buckets[i]->amountOfWords << ' ' << table->buckets[i]->amountOfAttempts << std::endl;
        }
        else
        {
            std::cout << '-' << std::endl;
        }
    }
}

bool existsString(HashTable *table, String *string)
{
    int step = 0;
    int newIndex = hash(string, table->capacity);
    do
    {
        newIndex = (newIndex + step) % table->capacity;
        step += 5;

        if (!table->buckets[newIndex])
        {
            return false;
        }
    }
    while (!isEqual(table->buckets[newIndex]->word, string));

    return true;
}
