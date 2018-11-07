#include <iostream>
using namespace std;

void printAsBinary(int value);
int addUpAsBinary(int first, int second);

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

    int sum = addUpAsBinary(firstNumber, secondNumber);

    cout << "The sum of this numbers: (" << sum << ")" << endl;
    printAsBinary(sum);

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

int addUpAsBinary(int first, int second)
{
    int result = first;

    while (second != 0)
    {
        result = first ^ second;

        second = (first & second) << 1;
        first = result;
    }

    return result;
}
