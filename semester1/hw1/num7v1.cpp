#include <iostream>
using namespace std;

int main()
{
    cout << "Enter value\n";
    int value = 0;
    cin >> value;

    bool isPrime[value + 1] = {};
    for (int k = 2; k <= value; k++)
    {
        isPrime[k] = true;
    }

    for (int i = 2; i <= value / 2; i++)
    {
        for (int j = i + i; j <= value; j += i)
        {
            isPrime[j] = false;
        }
    }

    cout << "All primes <= " << value << " are:\n";

    for (int k = 0; k <= value; k++)
    {
        if (isPrime[k])
        {
            cout << k << "\n";
        }
    }

    return 0;
}
