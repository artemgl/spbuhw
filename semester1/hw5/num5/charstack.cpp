#include "charstack.h"

CharStack *createCharStack()
{
    return new CharStack {nullptr};
}

void deleteCharStack(CharStack *stack)
{
    CharStackElement *current = stack->first;
    while (current)
    {
        CharStackElement *nextElement = current->next;
        delete current;
        current = nextElement;
    }

    delete stack;
}

void pushChar(CharStack *stack, char symbol)
{
    stack->first = new CharStackElement {symbol, stack->first};
}

char popChar(CharStack *stack)
{
    if (isEmptyChar(stack))
    {
        return '\0';
    }

    char symbol = stack->first->symbol;
    CharStackElement *nextElement = stack->first->next;
    delete stack->first;
    stack->first = nextElement;

    return symbol;
}

bool isEmptyChar(CharStack *stack)
{
    return !stack->first;
}
