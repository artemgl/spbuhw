#include <iostream>
#include "calculator.h"
using namespace std;

int main()
{
    int const maxLength = 256;

    cout << "Enter an expression without spaces" << endl;

    char inputLine[maxLength] = "";
    cin >> inputLine;

    transform(inputLine);

    cout << "The answer is ";
    calculate(inputLine);

    return 0;
}
