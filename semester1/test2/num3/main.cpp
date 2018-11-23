#include <iostream>
#include <fstream>
#include "list.h"
using namespace std;

int readNumber(char firstSymbol, ifstream &f);

int main()
{
    const char fName[] = "f.txt";
    const char gName[] = "g.txt";

    cout << "Enter a: ";
    int firstNumber = 0;
    cin >> firstNumber;
    cout << endl;

    cout << "Enter b: ";
    int secondNumber = 0;
    cin >> secondNumber;
    cout << endl;

    List *smallerList = createList();
    List *middleList = createList();
    List *biggerList = createList();

    ifstream f(fName);

    char symbol = '\0';
    while (f.get(symbol))
    {
        if (isdigit(symbol))
        {
            int currentNumber = readNumber(symbol, f);

            if (currentNumber < firstNumber)
            {
                addElement(smallerList, currentNumber);
            }
            else
            {
                if (currentNumber <= secondNumber)
                {
                    addElement(middleList, currentNumber);
                }
                else
                {
                    addElement(biggerList, currentNumber);
                }
            }
        }
    }

    f.close();

    writeListInFile(smallerList, gName);
    writeListInFile(middleList, gName);
    writeListInFile(biggerList, gName);

    deleteList(smallerList);
    deleteList(middleList);
    deleteList(biggerList);

    return 0;
}

int readNumber(char firstSymbol, ifstream &f)
{
    int result = 0;
    char currentSymbol = firstSymbol;

    while (isdigit(currentSymbol))
    {
        int a = currentSymbol - '0';
        result = result * 10 + a;
        f.get(currentSymbol);
    }

    return result;
}
