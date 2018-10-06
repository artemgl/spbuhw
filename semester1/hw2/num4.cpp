#include <iostream>
using namespace std;

void quickSort(int num[], int, int);

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

    quickSort(digit, 0, amountOfDigits - 1);

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

    delete[] digit;

    return 0;
}

void quickSort(int num[], int first, int last)
{
    if (first < last)
    {
        int length = last - first + 1;
        int select = first + length / 2;
        bool isSorted = false;
        while (!isSorted)
        {
            isSorted = true;
            for (int i = 0; i < select - first; i++)
            {
                if (num[first + i] > num[select])
                {
                    swap(num[first + i], num[select]);
                    select = first + i;
                    isSorted = false;
                    break;
                }
            }

            for (int i = 0; i < last - select; i++)
            {
                if (num[last - i] <= num[select])
                {
                    swap(num[last - i], num[select]);
                    select = last - i;
                    isSorted = false;
                    break;
                }
            }
        }

        quickSort(num, first, select - 1);
        quickSort(num, select + 1, last);
    }
}
