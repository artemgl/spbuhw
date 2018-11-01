#include <iostream>
#include "intstack.h"
using namespace std;

int main()
{
    int const maxLength = 256;

    cout << "Enter an expression without spaces in postfix entry" << endl;

    char inputLine[maxLength] = "";
    cin >> inputLine;

    IntStack *stack = createIntStack();

    for (int i = 0; inputLine[i] != '\0'; i++)
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

    return 0;
}
