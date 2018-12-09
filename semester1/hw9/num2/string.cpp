#include <iostream>
#include <fstream>
#include "string.h"
using namespace std;

String *createString(char string[])
{
    String *newString = new String {};
    newString->length = 0;
    for (int i = 0; string[i] != '\0'; i++)
    {
        newString->length++;
    }

    newString->symbols = new char[newString->length] {};
    for (int i = 0; i < newString->length; i++)
    {
        newString->symbols[i] = string[i];
    }

    return newString;
}

void deleteString(String *string)
{
    delete[] string->symbols;
    delete string;
}

bool isEmpty(String *string)
{
    return ((string->length == 0) ? true : false);
}

String *copy(String *string, int index, int countOfSymbols)
{
    String *newString = new String {};
    newString->symbols = new char[string->length] {};
    for (int i = 0; i < countOfSymbols; i++)
    {
        newString->symbols[i] = string->symbols[i + index];
    }

    newString->length = countOfSymbols;

    return newString;
}

String *clone(String *string)
{
    return copy(string, 0, string->length);
}

void concate(String *string, String *argument)
{
    for (int i = 0; i < argument->length; i++)
    {
        string->symbols[string->length + i] = argument->symbols[i];
    }
    string->length += argument->length;
}

int length(String *string)
{
    return string->length;
}

bool isEqual(String *firstString, String *secondString)
{
    if (firstString->length != secondString->length)
    {
        return false;
    }

    for (int i = 0; i < firstString->length; i++)
    {
        if (firstString->symbols[i] != secondString->symbols[i])
        {
            return false;
        }
    }

    return true;
}

char *represent(String *string)
{
    char *result = new char[string->length] {};
    for (int i = 0; i < string->length; i++)
    {
        result[i] = string->symbols[i];
    }

    return result;
}

void printString(String *string)
{
    for (int i = 0; i < string->length; i++)
    {
        cout << string->symbols[i];
    }
}

void printStringInFile(String *string, const char fileName[])
{
    ofstream fout(fileName, ios::app);

    for (int i = 0; i < string->length; i++)
    {
        fout << string->symbols[i];
    }

    fout.close();
}
