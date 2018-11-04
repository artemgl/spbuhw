#include <iostream>
using namespace std;

int numberOfDigits(int value);
void printCoefficient(int coefficient);

int main()
{
    cout << "Enter the largest exponent of polynomial" << endl;
    int size = 0;
    cin >> size;

    int *coefficient = new int[size + 1] {};

    for (int i = size; i >= 0; i--)
    {
        cout << "Enter the coefficient of " << i << " degree" << endl;
        cin >> coefficient[i];
    }

    cout << "The polynomial has the form:" << endl;

    for (int i = size; i > 0; i--)
    {
        if (coefficient[i] != 0)
        {
            if (coefficient[i] != 1 && coefficient[i] != - 1)
            {
                for (int j = 0; j < numberOfDigits(coefficient[i]); j++)
                {
                    cout << ' ';
                }
            }

            if (i == size)
            {
                if (coefficient[i] < 0)
                {
                    cout << ' ';
                }
            }
            else
            {
                cout << "  ";
            }
            cout << ' ';

            if (i == 1)
            {
                cout << ' ';
            }
            else
            {
                cout << i;
            }
        }
    }
    cout << endl;

    for (int i = size; i >= 0; i--)
    {
        if (coefficient[i] != 0)
        {
            if (i == size)
            {
                if (coefficient[i] < 0)
                {
                    cout << '-';
                }
            }
            else
            {
                cout << ((coefficient[i] > 0) ? "+" : "-") << ' ';
            }

            if (i == 0)
            {
                cout << ((coefficient[i] > 0) ? coefficient[i] : - coefficient[i]);
            }
            else
            {
                printCoefficient(coefficient[i]);
                cout << 'x';

                for (int j = 0; j < numberOfDigits(i); j++)
                {
                    cout << ' ';
                }
            }
        }
    }

    delete[] coefficient;

    return 0;
}

int numberOfDigits(int value)
{
    int result = 0;

    while (value != 0)
    {
        value /= 10;
        result++;
    }

    return result;
}

void printCoefficient(int coefficient)
{
    if (coefficient == 1 || coefficient == - 1)
    {
        return;
    }

    cout << ((coefficient > 0) ? coefficient : - coefficient);
}
