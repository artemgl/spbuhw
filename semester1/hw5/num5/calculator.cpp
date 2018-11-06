#include <iostream>
#include "charstack.h"
#include "intstack.h"

int length(char str[])
{
    char currentSymbol = '\0';
    int result = 0;
    do
    {
        currentSymbol = str[result++];
    }
    while (currentSymbol != '\0');

    return result - 1;
}

int priority(char c)
{
    switch (c)
    {
        case '+':
        case '-':
        {
            return 0;
            break;
        }
        case '*':
        case '/':
        {
             return 1;
             break;
        }
    }

    return - 1;
}


void transform(char inputLine[])
{
    int const maxLength = 256;

    char outputLine[maxLength] = "";

    CharStack *stack = createCharStack();

    for (int i = 0; i < length(inputLine); i++)
    {
        switch (inputLine[i])
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            {
                outputLine[length(outputLine)] = inputLine[i];
                break;
            }
            case '+':
            case '-':
            case '*':
            case '/':
            {
                char currentSymbol = '\0';
                while (priority(currentSymbol = popChar(stack)) >= priority(inputLine[i]))
                {
                    outputLine[length(outputLine)] = currentSymbol;
                }

                pushChar(stack, currentSymbol);
                pushChar(stack, inputLine[i]);
                break;
            }
            case '(':
            {
                pushChar(stack, inputLine[i]);
                break;
            }
            case ')':
            {
                char currentSymbol = '\0';
                while ((currentSymbol = popChar(stack)) != '(')
                {
                    outputLine[length(outputLine)] = currentSymbol;
                }

                break;
            }
        }
    }

    while (!isEmptyChar(stack))
    {
        outputLine[length(outputLine)] = popChar(stack);
    }

    deleteCharStack(stack);

    for (int i = 0; i < length(outputLine); i++)
    {
        inputLine[i] = outputLine[i];
        inputLine[i + 1] = '\0';
    }
}

void calculate(char inputLine[])
{
    IntStack *stack = createIntStack();

    for (int i = 0; i < length(inputLine); i++)
    {
        switch (inputLine[i])
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            {
                pushInt(stack, inputLine[i] - '0');
                break;
            }
            case '+':
            {
                int second = popInt(stack);
                int first = popInt(stack);
                pushInt(stack, first + second);
                break;
            }
            case '-':
            {
                int second = popInt(stack);
                int first = popInt(stack);
                pushInt(stack, first - second);
                break;
            }
            case '*':
            {
                int second = popInt(stack);
                int first = popInt(stack);
                pushInt(stack, first * second);
                break;
            }
            case '/':
            {
                int second = popInt(stack);
                int first = popInt(stack);
                pushInt(stack, first / second);
                break;
            }
        }
    }

    printIntStack(stack);

    deleteIntStack(stack);
}

