#include "hashtable.h"
#include "list.h"

const int capacity = 10007;

HashTable *createHashTable()
{
    HashTable *table = new HashTable {};

    table->capacity = capacity;
    table->size = 0;

    table->buckets = new List *[capacity] {};

    for (int i = 0; i < capacity; i++)
    {
        table->buckets[i] = nullptr;
    }

    return table;
}

void deleteHashTable(HashTable *table)
{
    for (int i = 0; i < capacity; i++)
    {
        if (table->buckets[i] != nullptr)
        {
            deleteList(table->buckets[i]);
        }
    }

    delete table->buckets;
    delete table;
}

double loadFactor(HashTable *table)
{
    return (double)table->size / table->capacity;
}

int hashInt(int number)
{
    number ^= (number << 13);
    number ^= (number >> 17);
    number ^= (number << 5);

    return number % capacity;
}

void addElement(HashTable *table, int firstNumber, int secondNumber)
{
    int hash = hashInt(firstNumber);

    if (exists(table->buckets[hash], firstNumber, secondNumber))
    {
        return;
    }

    if (!table->buckets[hash])
    {
        table->buckets[hash] = createList();
    }

    addElement(table->buckets[hash], firstNumber, secondNumber);

    table->size++;
}

void removeElement(HashTable *table, int firstNumber, int secondNumber)
{
    int hash = hashInt(firstNumber);

    if (table->buckets[hash] == nullptr || !exists(table->buckets[hash], firstNumber, secondNumber))
    {
        return;
    }

    removeElement(table->buckets[hash], firstNumber, secondNumber);

    if (isEmpty(table->buckets[hash]))
    {
        deleteList(table->buckets[hash]);
    }

    table->size--;
}

int returnSecondNumber(HashTable *table, int firstNumber)
{
    int hash = hashInt(firstNumber);

    if (table->buckets[hash])
    {
        ListElement *current = table->buckets[hash]->first;
        while (current)
        {
            if (current->firstNumber == firstNumber)
            {
                return current->secondNumber;
            }
        }
    }

    return 0;
}
