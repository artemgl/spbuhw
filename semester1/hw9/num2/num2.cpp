#include <iostream>
#include <fstream>
#include "priorityqueue.h"
#include "hashtable.h"
using namespace std;

int main()
{
    const char fileForReadingName[] = "input.txt";
    const char fileForWritingName[] = "output.txt";

    PriotityQueue *priorityQueue = createPriorityQueue();

    ifstream file(fileForReadingName);

    char symbol = '\0';
    file.get(symbol);
    while (!file.eof())
    {
        addElement(priorityQueue, symbol);
        file.get(symbol);
    }

    file.close();

    printPriorityQueue(priorityQueue);

    Tree *tree = buildTree(priorityQueue);
    printTreeInFile(tree, fileForWritingName);

    HashTable *table = buildHashTable(tree);
    deleteTree(tree);

    encryptInFile(table, fileForReadingName, fileForWritingName);
    deleteHashTable(table);

    return 0;
}
