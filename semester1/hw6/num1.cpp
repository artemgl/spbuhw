#include <iostream>
using namespace std;

void printRepresented(double value);

int main()
{
    cout << "Enter a number: ";
    double value = 0;
    cin >> value;

    cout << "Result: ";
    printRepresented(value);

    return 0;
}

void printRepresented(double value)
{
    if (value == 0)
    {
        cout << 0 << endl;
        return;
    }

    unsigned char *x = (unsigned char *)&value;

    cout << ((x[7] & 0b10000000) ? "-" : "+");

    int exponent = (((short int)x[7] & 0b01111111) << 4) + ((int)x[6] >> 4) - 1023;

    x[6] &= 0b00001111;
    x[6] += 0b11110000;

    x[7] = 0b00111111;

    cout << value;
    cout << "*2^(" << exponent << ")" << endl;
}
