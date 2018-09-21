#include <iostream>
using namespace std;

int main()
{
    cout << "Enter x\n";

    int valueX = 0;
    cin >> valueX;

    int squareX = valueX * valueX;

    cout << "Value of the expression is ";
    cout << (squareX + 1) * (squareX + valueX) + 1;
    cout << "\n";

    return 0;
}
