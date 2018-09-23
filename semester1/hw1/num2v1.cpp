#include <iostream>
using namespace std;

int main()
{
    cout << "Enter dividend\n";
    int dividend = 0;
    cin >> dividend;

    cout << "Enter divider\n";
    int divider = 1;
    cin >> divider;

    cout << "Incomplete quotient of " << dividend << " / " << divider << " = ";

    if (divider < 0)
    {
        dividend *= - 1;
        divider *= - 1;
    }

    int remainder = dividend;
    int k = 0;
    while (!(remainder >= 0 && remainder < divider))
    {
        if (dividend < 0)
        {
            remainder += divider;
        }
        else
        {
            remainder -= divider;
        }

        k++;
    }

    if (dividend < 0)
    {
        cout << "-";
    }

    cout << k;

    return 0;
}
