#pragma once

struct Symbol
{
    char symbol;
    Symbol *next;
};

struct String
{
    Symbol *firstSymbol;
};

String *createString(char string[]);
void deleteString(String *string);

String *clone(String *string);
String *copy(String *string, int index, int countOfSymbols);
void concate(String *string, String *argument);

bool isEqual(String *firstString, String *secondString);
bool isEmpty(String *string);

int length(String *string);

char returnSymbol(String *string, int index);

char *represent(String *string);
