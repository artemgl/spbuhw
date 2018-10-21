#pragma once

struct IntStackElement
{
    int number;
    IntStackElement *next;
};

struct IntStack
{
    IntStackElement *first;
};

IntStack *createIntStack();
void deleteIntStack(IntStack *stack);

void pushInt(IntStack *stack, int number);
char popInt(IntStack *stack);

void printIntStack(IntStack *stack);

bool isEmptyInt(IntStack *stack);
