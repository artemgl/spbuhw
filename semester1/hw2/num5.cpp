#include <iostream>
using namespace std;

int pow(int a, int n);

void pyramidalsort(int arr[], int size);

int main()
{
    cout << "Enter the size of array\n";
    int size = 1;
    cin >> size;

    int *arr = new int[size] {};

    for (int i = 0; i < size; i++)
    {
        arr[i] = rand() % 100 + 1;
    }

    pyramidalsort(arr, size);

    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << ", ";
    }

    return 0;
}

int pow(int a, int n)
{
    int result = 1;

    for (int i = 1; i <= n; i++)
    {
        result *= a;
    }

    return result;
}

void pyramidalsort(int arr[], int size)
{
    int amountOfLevels = 0;
    while (size > 1)
    {
        amountOfLevels = 1;
        while (!(pow(2, amountOfLevels - 1) <= size && size < pow(2, amountOfLevels)))
        {
            amountOfLevels++;
        }

        for (int i = amountOfLevels - 1; i >= 1; i--)
        {
            for (int j = pow(2, i - 1) - 1; j < pow(2, i) - 1; j++)
            {
                if (2 * j + 1 < size)
                {
                    if (2 * j + 2 < size)
                    {
                        if (arr[2 * j + 1] > arr[2 * j + 2])
                        {
                            if (arr[2 * j + 1] > arr[j])
                            {
                                swap(arr[2 * j + 1], arr[j]);
                            }
                        }
                        else
                        {
                            if (arr[2 * j + 2] > arr[j])
                            {
                                swap(arr[2 * j + 2], arr[j]);
                            }
                        }
                    }
                    else
                    {
                        if (arr[2 * j + 1] > arr[j])
                        {
                            swap(arr[2 * j + 1], arr[j]);
                        }
                    }
                }
            }
        }

        swap(arr[0], arr[size - 1]);
        size--;
    }
}
