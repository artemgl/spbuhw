#include <iostream>
#include <fstream>
#include "list.h"
using namespace std;

List *createList()
{
    return new List {nullptr};
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        list->first = current->next;
        delete current;
        current = list->first;
    }

    delete list;
}

void addElement(ListElement *&current, int value)
{
    if (current)
    {
        addElement(current->next, value);
        return;
    }

    current = new ListElement {value, nullptr};
}

void addElement(List *list, int value)
{
    addElement(list->first, value);
}

void removeElement(ListElement *&listElement, int value)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->value == value)
    {
        ListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, value);
}

void removeElement(List *list, int value)
{
    removeElement(list->first, value);
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->value << endl;
        current = current->next;
    }

    cout << endl;
}

bool isEmpty(List *list)
{
    return !(list->first);
}

bool exists(List *list, int value)
{
    ListElement *current = list->first;

    while (current)
    {
        if (current->value == value)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}

int numberOfDigits(int number)
{
    int result = 0;

    while (number != 0)
    {
        number /= 10;
        result++;
    }

    return result;
}

void writeNumberInFile(int number, ofstream &fout)
{
    int amountOfDigits = numberOfDigits(number);

    char *inputLine = new char[amountOfDigits] {};

    for (int i = 0; i < amountOfDigits; i++)
    {
        inputLine[i] = (number % 10) + '0';
        number /= 10;
    }

    for (int i = amountOfDigits - 1; i >= 0; i--)
    {
        fout.put(inputLine[i]);
    }

    delete[] inputLine;
}

void writeListInFile(List *list, const char fileName[])
{
    ListElement *current = list->first;

    ofstream fout(fileName, ios::app);

    while (current)
    {
        writeNumberInFile(current->value, fout);
        fout.put(' ');
        current = current->next;
    }

    fout.close();
}
