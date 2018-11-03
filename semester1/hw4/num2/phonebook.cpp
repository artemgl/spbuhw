#include "list.h"
#include <iostream>
#include <fstream>
using namespace std;

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
    while (current)
    {
        if (isEqual(current->number, number))
        {
            cout << current->name << endl;
        }
        current = current->next;
    }
}

void printNumber(List *list, char name[])
{
    ListElement *current = list->first;
    while (current)
    {
        if (isEqual(current->name, name))
        {
            cout << current->number << endl;
        }
        current = current->next;
    }
}

void saveInFile(List *list, char const fileName[])
{
    ofstream fout(fileName, ios::app);

    ListElement *current = list->first;
    while (current)
    {
        fout << ';' << current->name << '-' << current->number << ';';
        current = current->next;
    }

    fout.close();
}
