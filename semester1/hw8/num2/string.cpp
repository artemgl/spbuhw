#include "string.h"

void addSymbol(Symbol *&currentSymbol, char symbol)
{
    if (!currentSymbol)
    {
        currentSymbol = new Symbol {symbol, nullptr};
        return;
    }

    addSymbol(currentSymbol->next, symbol);
}

String *createString(char string[])
{
    String *newString = new String {};

    for (int i = 0; string[i] != '\0'; i++)
    {
        addSymbol(newString->firstSymbol, string[i]);
    }

    return newString;
}

void deleteString(String *string)
{
    Symbol *current = string->firstSymbol;
    while (current)
    {
        string->firstSymbol = current->next;
        delete current;
        current = string->firstSymbol;
    }

    delete string;
}

bool isEmpty(String *string)
{
    return !string->firstSymbol;
}

String *clone(String *string)
{
    String *newString = createString("");
    Symbol *current = string->firstSymbol;

    while (current)
    {
        addSymbol(newString->firstSymbol, current->symbol);
        current = current->next;
    }

    return newString;
}

String *copy(String *string, int index, int countOfSymbols)
{
    String *newString = createString("");
    Symbol *current = string->firstSymbol;

    for (int i = 0; i < index; i++)
    {
        current = current->next;
    }

    for (int i = 0; i < countOfSymbols; i++)
    {
        addSymbol(newString->firstSymbol, current->symbol);
        current = current->next;
    }

    return newString;
}

void concate(String *string, String *argument)
{
    String *extraString = clone(argument);

    if (isEmpty(string))
    {
        string->firstSymbol = extraString->firstSymbol;
    }
    else
    {
        Symbol *current = string->firstSymbol;

        while (current->next)
        {
            current = current->next;
        }

        current->next = extraString->firstSymbol;
    }

    delete extraString;
}

int length(String *string)
{
    if (isEmpty(string))
    {
        return 0;
    }

    Symbol *current = string->firstSymbol;

    int result = 1;
    while (current->next)
    {
        current = current->next;
        result++;
    }

    return result;
}

char returnSymbol(String *string, int index)
{
    Symbol *current = string->firstSymbol;

    for (int i = 0; i < index; i++)
    {
        current = current->next;
    }

    return current->symbol;
}

bool isEqual(String *firstString, String *secondString)
{
    int lengthOfString = length(firstString);
    if (lengthOfString == length(secondString))
    {
        for (int i = 0; i < lengthOfString; i++)
        {
            if (returnSymbol(firstString, i) != returnSymbol(secondString, i))
            {
                return false;
            }
        }

        return true;
    }

    return false;
}

char *represent(String *string)
{
    int lengthOfString = length(string);
    char *newString = new char[lengthOfString] {};

    for (int i = 0; i < lengthOfString; i++)
    {
        newString[i] = returnSymbol(string, i);
    }

    newString[lengthOfString] = '\0';

    return newString;
}
