#include <iostream>
using namespace std;

int factorialRecursive(int);
int factorialIterative(int);

int main()
{
    cout << "Enter n\n";
    int n = 0;
    cin >> n;

    cout << "Factorial of " << n << " is:\n";
    cout << factorialRecursive(n) << "\n";
    cout << factorialIterative(n) << "\n";

    return 0;
}

int factorialRecursive(int x)
{
    int result = 1;

    if (x > 1)
    {
        result = factorialRecursive(x - 1) * x;
    }

    return result;
}

int factorialIterative(int x)
{
    int result = 1;

    if (x > 1)
    {
        for (int i = 2; i <= x; i++)
        {
            result *= i;
        }
    }

    return result;
}
