#include <iostream>
using namespace std;

int FibonacciIterative(int);
int FibonacciRecursive(int);

int main()
{
    cout << "Enter sequence number of the Fibonacci sequence\n";
    int number = 0;
    cin >> number;

    cout << FibonacciIterative(number) << "\n";
    cout << FibonacciRecursive(number) << "\n";

    return 0;
}

int FibonacciIterative(int a)
{
    int buffer = 0, result = 1;

    if (a == 0)
    {
        result = 0;
    }

    while (a > 1)
    {
        buffer += result;
        swap(buffer, result);

        a--;
    }

    return result;
}

int FibonacciRecursive(int a)
{
    int result = 0;

    if (a == 1)
    {
        result = 1;
    }

    if (a > 1)
    {
        result = FibonacciRecursive(a - 2) + FibonacciRecursive(a - 1);
    }

    return result;
}
