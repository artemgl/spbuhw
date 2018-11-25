#include "list.h"
#include "phonebook.h"
#include <iostream>
#include <fstream>
using namespace std;

struct ListElement
{
    char name[maxLength];
    char number[maxLength];
    ListElement *next;
};

struct List
{
    ListElement *first;
};

void addNote(List *list, char name[], char number[])
{
    addElement(list, name, number);
}

int length(char inputLine[])
{
    int result = 0;
    while (inputLine[result++] != '\0');

    return result - 1;
}

bool isEqual(char firstLine[], char secondLine[])
{
    bool result = true;
    if (length(firstLine) == length(secondLine))
    {
        for (int i = 0; firstLine[i] != '\0' && secondLine[i] != '\0'; i++)
        {
            if (firstLine[i] != secondLine[i])
            {
                result = false;
                break;
            }
        }
    }
    else
    {
        result = false;
    }

    return result;
}

void printName(List *list, char number[])
{
    ListElement *current = list->first;
    bool exists = false;
    while (current)
    {
        if (isEqual(current->number, number))
        {
            cout << current->name << endl;
            exists = true;
        }
        current = current->next;
    }

    if (!exists)
    {
        cout << "Not found" << endl;
    }
}

void printNumber(List *list, char name[])
{
    ListElement *current = list->first;
    bool exists = false;
    while (current)
    {
        if (isEqual(current->name, name))
        {
            cout << current->number << endl;
            exists = true;
        }
        current = current->next;
    }

    if (!exists)
    {
        cout << "Not found" << endl;
    }
}

void copyFromFile(List *list, char const fileName[])
{
    ifstream fin(fileName);

    char name[maxLength] = "";
    char number[maxLength] = "";

    char currentSymbol = '\0';
    fin.get(currentSymbol);
    while (!fin.eof())
    {
        for (int i = 0; i < maxLength; i++)
        {
            name[i] = '\0';
            number[i] = '\0';
        }

        currentSymbol = '\0';
        int i = 0;
        while (currentSymbol != '-')
        {
            fin.get(currentSymbol);
            name[i++] = currentSymbol;
        }
        name[i - 1] = '\0';

        currentSymbol = '\0';
        i = 0;
        while (currentSymbol != ';')
        {
            fin.get(currentSymbol);
            number[i++] = currentSymbol;
        }
        number[i - 1] = '\0';

        addElement(list, name, number);

        fin.get(currentSymbol);
    }

    fin.close();
}

void saveInFile(List *list, char const fileName[])
{
    ofstream fout(fileName);

    ListElement *current = list->first;
    while (current)
    {
        fout << ';' << current->name << '-' << current->number << ';';
        current = current->next;
    }

    fout.close();
}
