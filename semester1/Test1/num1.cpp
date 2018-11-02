#include <iostream>
using namespace std;

int main()
{
    cout << "Enter the length of your string" << endl;
    int length = 0;
    cin >> length;

    cout << "Enter the string" << endl;
    char *inputLine = new char[length] {};
    for (int i = 0; i <= length; i++)
    {
        char currentSymbol = '\0';
        scanf("%c", &currentSymbol);
        if (i > 0)
        {
            inputLine[i - 1] = currentSymbol;
        }
    }

    cout << "Enter the necessary length" << endl;
    int necessaryLength = 0;
    cin >> necessaryLength;

    int spaceAmount = 0;
    for (int i = 1; i + 1 < length; i++)
    {
        if (inputLine[i] == ' ' && isalpha(inputLine[i - 1]))
        {
            spaceAmount++;
        }
    }

    int minimumAmountOfSpaces = (necessaryLength - length) / spaceAmount;
    int extraAmountOfSpaces = (necessaryLength - length) % spaceAmount;

    for (int i = 0; i < length; i++)
    {
        if (inputLine[i] == ' ' && isalpha(inputLine[i - 1]))
        {
            for (int j = 0; j <= minimumAmountOfSpaces; j++)
            {
                cout << ' ';
            }

            if (extraAmountOfSpaces > 0)
            {
                cout << ' ';
                extraAmountOfSpaces--;
            }

            while (!isalpha(inputLine[i + 1]))
            {
                i++;
            }
        }
        else
        {
            cout << inputLine[i];
        }
    }

    delete[] inputLine;

    return 0;
}
