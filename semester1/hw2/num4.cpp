#include <iostream>
using namespace std;

void sort(int arr[], int size);

int main()
{
    cout << "Enter number\n";
    int number = 0;
    cin >> number;

    int clone = number;
    int amountOfDigits = 0;
    while (clone > 0)
    {
        clone /= 10;
        amountOfDigits++;
    }

    int *digit = new int[amountOfDigits] {};

    for (int i = amountOfDigits - 1; i >= 0; i--)
    {
        digit[i] = number % 10;
        number /= 10;
    }

    sort(digit, amountOfDigits);

    if (digit[0] == 0)
    {
        int j = 0;
        while (digit[j] == 0)
        {
            j++;
        }

        swap(digit[0], digit[j]);
    }

    cout << "The smallest number written in the same numerals is: \n";

    for (int i = 0; i < amountOfDigits; i++)
    {
        cout << digit[i];
    }

    return 0;
}

void sort(int arr[], int size)
{
    for (int i = size - 1; i >= 1; i--)
    {
        for (int j = 1; j <= i; j++)
        {
            if (arr[j - 1] > arr[j])
            {
                swap(arr[j - 1], arr[j]);
            }
        }
    }
}
