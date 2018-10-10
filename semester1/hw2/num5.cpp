#include <iostream>
#include <time.h>
using namespace std;

int pow(int a, int n);

void pyramidalSort(int arr[], int size);

int main()
{
    cout << "Enter the size of array\n";
    int size = 1;
    cin >> size;

    int *arr = new int[size] {};

    srand(time(NULL));
    for (int i = 0; i < size; i++)
    {
        arr[i] = rand() % 100 + 1;
    }

    pyramidalSort(arr, size);

    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << ", ";
    }

    delete[] arr;

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

void pyramidalSort(int arr[], int size)
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
                int leftIndex = 2 * j + 1;
                int rightIndex = 2 * j + 2;

                if (leftIndex < size)
                {
                    if (rightIndex < size)
                    {
                        if (arr[leftIndex] > arr[rightIndex])
                        {
                            if (arr[leftIndex] > arr[j])
                            {
                                swap(arr[leftIndex], arr[j]);
                            }
                        }
                        else
                        {
                            if (arr[rightIndex] > arr[j])
                            {
                                swap(arr[rightIndex], arr[j]);
                            }
                        }
                    }
                    else
                    {
                        if (arr[leftIndex] > arr[j])
                        {
                            swap(arr[leftIndex], arr[j]);
                        }
                    }
                }
            }
        }

        swap(arr[0], arr[size - 1]);
        size--;
    }
}
