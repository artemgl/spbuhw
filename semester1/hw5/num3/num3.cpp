#include <iostream>
#include "charstack.h"
using namespace std;

int length(string str);
int priority(char c);

int main()
{
    string str = "";
    getline(cin, str);

    string outputLine = "";

    CharStack *stack = createCharStack();

    for (int i = 0; i < length(str); i++)
    {
        switch (str[i])
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
                outputLine += str[i];
                break;
            }
            case '+':
            case '-':
            case '*':
            case '/':
            {
                char currentSymbol = '\0';
                while (priority(currentSymbol = popChar(stack)) >= priority(str[i]))
                {
                    outputLine += currentSymbol;
                }

                pushChar(stack, currentSymbol);
                pushChar(stack, str[i]);
                break;
            }
            case '(':
            {
                pushChar(stack, str[i]);
                break;
            }
            case ')':
            {
                char currentSymbol = '\0';
                while ((currentSymbol = popChar(stack)) != '(')
                {
                    outputLine += currentSymbol;
                }

                break;
            }
        }
    }

    while (!isEmptyChar(stack))
    {
        outputLine += popChar(stack);
    }

    cout << outputLine;

    deleteCharStack(stack);

    return 0;
}

int length(string str)
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
