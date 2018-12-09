#pragma once

struct String
{
    int length;
    char *symbols;
};

String *createString(char string[]);
void deleteString(String *string);

String *clone(String *string);
String *copy(String *string, int index, int countOfSymbols);
void concate(String *string, String *argument);

bool isEqual(String *firstString, String *secondString);
bool isEmpty(String *string);

int length(String *string);

char *represent(String *string);

void printString(String *string);
void printStringInFile(String *string, const char fileName[]);
