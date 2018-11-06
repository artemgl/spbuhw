#include <iostream>
#include "intstack.h"
using namespace std;

IntStack *createIntStack()
{
    return new IntStack {nullptr};
}

void deleteIntStack(IntStack *stack)
{
    IntStackElement *current = stack->first;
    while (current)
    {
        IntStackElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }

    delete stack;
}

void pushInt(IntStack *stack, int number)
{
    stack->first = new IntStackElement {number, stack->first};
}

int popInt(IntStack *stack)
{
    if (isEmptyInt(stack))
    {
        return '\0';
    }

    int number = stack->first->number;
    IntStackElement *nextElement = stack->first->next;
    delete stack->first;
    stack->first = nextElement;

    return number;
}

void printIntStack(IntStack *stack)
{
    IntStackElement *current = stack->first;
    while (current)
    {
        cout << current->number << " ";
        current = current->next;
    }

    cout << endl;
}

bool isEmptyInt(IntStack *stack)
{
    return !stack->first;
}
