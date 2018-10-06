#include <iostream>
using namespace std;

void quickSort(int num[], int, int);

int main()
{
    int size = 0;
    cout << "Enter the size of array\n";
    cin >> size;

    int *arr = new int[size] {};

    for (int i = 0; i < size; i++)
    {
        cout << "Enter the " << i + 1 << " element\n";
        cin >> arr[i];
    }

    quickSort(arr, 0, size - 1);

    for (int j = size - 2; j >= 0; j--)
    {
        if (arr[j] == arr[j + 1])
        {
            cout << "The largest repeated element is " << arr[j];
            break;
        }
    }

    delete[] arr;

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
