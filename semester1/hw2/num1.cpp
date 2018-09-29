#include <iostream>
using namespace std;

int fibonacciIterative(int);
int fibonacciRecursive(int);

int main()
{
    cout << "Enter sequence number of the Fibonacci sequence\n";
    int number = 0;
    cin >> number;

    cout << fibonacciIterative(number) << "\n";
    cout << fibonacciRecursive(number) << "\n";

    return 0;
}

int fibonacciIterative(int a)
{
    int result = 1;
    int buffer = 0;

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

int fibonacciRecursive(int a)
{
    int result = 0;

    if (a == 1)
    {
        result = 1;
    }

    if (a > 1)
    {
        result = fibonacciRecursive(a - 2) + fibonacciRecursive(a - 1);
    }

    return result;
}
