#include <iostream>
using namespace std;

int main()
{
    int a = 0;
    int sumOfDigits = 0;
    int const max = 28;
    int combinations[max] = {};

    for (int i = 0; i < 1000; i++)
    {
        a = i;
        while (a > 0)
        {
            sumOfDigits += a % 10;
            a /= 10;
        }

        combinations[sumOfDigits]++;
        sumOfDigits = 0;
    }

    int answer = 0;

    for (int j = 0; j < max / 2; j++)
    {
        answer += combinations[j] * combinations[j];
    }

    cout << "Amount of lucky tickets is " << answer * 2;

    return 0;
}
