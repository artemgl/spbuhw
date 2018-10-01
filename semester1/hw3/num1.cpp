#include <iostream>
using namespace std;

void sort(int arr[], int size);

int main()
{
    int const size = 13;

    int *arr = new int[size] {10, 4, 6, 3, 8, 6, 7, 9, 2, 9, 1, 11, 5};

    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << ", ";
    }

    sort(arr, size);

    for (int j = size - 2; j >= 0; j--)
    {
        if (arr[j] == arr[j + 1])
        {
            cout << "\n" << arr[j];
            break;
        }
    }

    delete[] arr;

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
