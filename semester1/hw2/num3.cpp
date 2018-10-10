#include <iostream>
using namespace std;

int main()
{
    cout << "Enter n\n";
    int maxDenominator = 1;
    cin >> maxDenominator;

    int a = 0;
    int b = 1;
    int c = 1;
    int d = maxDenominator;
    while (c < d)
    {
        cout << c << "/" << d << ", ";

        a = c * ((maxDenominator + b) / d) - a;
        b = d * ((maxDenominator + b) / d) - b;

        swap(a, c);
        swap(b, d);
    }

    return 0;
}
