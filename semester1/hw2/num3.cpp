#include <iostream>
using namespace std;

int gcd(int, int);
int lcm(int, int);

int main()
{
    cout << "Enter n\n";
    int maxDenominator = 1;
    cin >> maxDenominator;

    int a = 0, b = 1, c = 1, d = maxDenominator;
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
