#include <iostream>
using namespace std;

int factorial(int);

int main()
{
    cout << "Enter n\n";
    int n = 0;
    cin >> n;

    cout << "Factorial of " << n << " is ";
    cout << factorial(n) << "\n";

    return 0;
}

int factorial(int x)
{
    int result = 1;

    if (x > 1)
    {
        result = factorial(x - 1) * x;
    }

    return result;
}
