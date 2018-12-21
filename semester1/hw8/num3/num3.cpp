#include <iostream>
#include <fstream>
#include "hashtable.h"
using namespace std;

String *readWordFromFile(ifstream &fin);

const int maxLength = 256;

int main()
{
    const char fileName[] = "file.txt";

    HashTable *table = createHashTable();

    ifstream fin(fileName);
    while (!fin.eof())
    {
        String *currentWord = readWordFromFile(fin);
        if (currentWord)
        {
            addElement(table, currentWord);
            deleteString(currentWord);
        }
    }
    fin.close();

    cout << "Load factor: " << loadFactor(table) << endl;
    cout << "Average amount of attempts: " << averageAmountOfAttempts(table) << endl;
    cout << "Amount of words: " << amountOfWords(table) << endl;
    cout << "Amount of empty buckets: " << amountOfEmptyBuckets(table) << endl;
    cout << "Max amount of attempts: " << maxAmountOfAttempts(table) << endl;
    cout << "Chains with max amount of attempts:" << endl;
    printWordsWithMaxAmountOfAttempts(table);

    deleteHashTable(table);

    return 0;
}

String *readWordFromFile(ifstream &fin)
{
    char symbol = '\0';
    do
    {
        fin.get(symbol);
        if (fin.eof())
        {
            return nullptr;
        }
    }
    while (!isalpha(symbol));

    char word[maxLength] = {};
    for (int i = 0; !fin.eof() && isalpha(symbol); i++)
    {
        word[i] = symbol;
        fin.get(symbol);
    }

    return createString(word);
}
