#include <iostream>
#include "string.h"
using namespace std;

int main()
{
    cout << "Enter:" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - print cloned string" << endl;
    cout << "2 - print substring" << endl;
    cout << "3 - print concatenated string" << endl;
    cout << "4 - print string transformed to char*" << endl;
    cout << "5 - find out the length of the string" << endl;
    cout << "6 - check if strings are equal" << endl;
    cout << "7 - check if string is empty" << endl;

    int input = 0;
    do
    {
        cin >> input;

        switch (input)
        {
        case 1:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "Cloned string: ";
            String *clonedString = clone(currentString);
            printString(clonedString);

            deleteString(clonedString);
            deleteString(currentString);
            break;
        }
        case 2:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "The first index: ";
            int firstIndex = 0;
            cin >> firstIndex;

            cout << "The last index: ";
            int lastIndex = 0;
            cin >> lastIndex;

            cout << "Substring: ";
            String *copiedString = copy(currentString, firstIndex, lastIndex - firstIndex + 1);
            printString(copiedString);

            deleteString(copiedString);
            deleteString(currentString);
            break;
        }
        case 3:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "The string-argument: ";
            char extraString[] = "";
            cin >> extraString;

            String *argument = createString(extraString);
            cout << "Concatenated string: ";
            concate(currentString, argument);
            printString(currentString);

            deleteString(argument);
            deleteString(currentString);
            break;
        }
        case 4:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            char *newString = represent(currentString);
            cout << "String transormed to char*: " << newString << endl;

            delete[] newString;
            deleteString(currentString);
            break;
        }
        case 5:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "The length of this string: " << length(currentString) << endl;

            deleteString(currentString);
            break;
        }
        case 6:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "The second string: ";
            char extraString[] = "";
            cin >> extraString;

            String *secondString = createString(extraString);

            cout << "This strings are" << (isEqual(currentString, secondString) ? "" : " not") << " equal" << endl;

            deleteString(secondString);
            deleteString(currentString);
            break;
        }
        case 7:
        {
            cout << "The string: ";
            char inputLine[] = "";
            cin >> inputLine;
            String *currentString = createString(inputLine);

            cout << "This string is" << (isEmpty(currentString) ? "" : " not") << " empty" << endl;

            deleteString(currentString);
            break;
        }
        }
    }
    while (input);

    return 0;
}
