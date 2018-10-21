#include <iostream>
#include "intstack.h"
using namespace std;

int length(string str);

int main()
{
    string str = "";
    getline(cin, str);

    IntStack *stack = createIntStack();

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
                pushInt(stack, str[i] - '0');
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
