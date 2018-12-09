#include <iostream>
#include <fstream>
#include "list.h"
#include "hashtable.h"
using namespace std;

int main()
{
    const char fileForReadingName[] = "input.txt";
    const char fileForWritingName[] = "output.txt";

    List *list = createList();

    ifstream file(fileForReadingName);

    char symbol = '\0';
    file.get(symbol);
    while (!file.eof())
    {
        addElement(list, symbol);
        file.get(symbol);
    }

    file.close();

    printList(list);

    Tree *tree = buildTree(list);
    printTreeInFile(tree, fileForWritingName);

    HashTable *table = buildHashTable(tree);
    deleteTree(tree);

    encryptInFile(table, fileForReadingName, fileForWritingName);
    deleteHashTable(table);

    return 0;
}
