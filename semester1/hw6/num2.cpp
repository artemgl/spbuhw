#include <iostream>
using namespace std;

void printAsBinary(int value);

int main()
{
    cout << "Enter the first number" << endl;
    int firstNumber = 0;
    cin >> firstNumber;

    cout << "Enter the second number" << endl;
    int secondNumber = 0;
    cin >> secondNumber;

    cout << "Binary first number:" << endl;
    printAsBinary(firstNumber);

    cout << "Binary second number:" << endl;
    printAsBinary(secondNumber);

    cout << "The sum of this numbers: (" << firstNumber + secondNumber << ")" << endl;
    printAsBinary(firstNumber + secondNumber);

    return 0;
}

void printAsBinary(int value)
{
    unsigned char *byte = (unsigned char *)&value;

    for (int i = 3; i >= 0; i--)
    {
        int bit = 0b10000000;
        for (int j = 0; j < 8; j++)
        {
            cout << ((byte[i] & bit) ? "1" : "0");
            bit = bit >> 1;
        }
        cout << ' ';
    }
    cout << endl;
}
