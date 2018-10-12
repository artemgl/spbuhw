#include <iostream>
using namespace std;

void enterArr(int arr[], int size);
void sort(int arr1[], int size1, int arr2[], int size2, int first1, int first2, int arr[], int &k);
void printArr(int arr[], int size);

int main()
{
    int size1 = 0;
    cout << "Enter the first size\n";
    cin >> size1;

    int *arr1 = new int[size1] {};
    enterArr(arr1, size1);

    int size2 = 0;
    cout << "Enter the second size\n";
    cin >> size2;

    int *arr2 = new int[size2] {};
    enterArr(arr2, size2);

    int *arr = new int[size1 + size2] {};
    int k = 0;

    sort(arr1, size1, arr2, size2, 0, 0, arr, k);

    cout << "The first array: ";
    printArr(arr1, size1);

    cout << "The second array: ";
    printArr(arr2, size2);

    cout << "Merged arrays: ";
    printArr(arr, size1 + size2);

    delete []arr1;
    delete []arr2;
    delete []arr;

    cout << "Difficulty is O(n)";

    return 0;
}

void enterArr(int arr[], int size)
{
    for (int i = 0; i < size; i++)
    {
        cout << "Enter the " << i + 1 << " element\n";
        cin >> arr[i];
    }
}

void sort(int arr1[], int size1, int arr2[], int size2, int first1, int first2, int arr[], int &k)
{
    if (first1 == size1)
    {
        for (int i = first2; i < size2; i++)
        {
            arr[k] = arr2[i];
            k++;
        }

        return;
    }

    if (first2 == size2)
    {
        for (int i = first1; i < size1; i++)
        {
            arr[k] = arr1[i];
            k++;
        }

        return;
    }

    if (arr1[first1] >= arr2[first2])
    {
        arr[k] = arr1[first1];
        k++;
        sort(arr1, size1, arr2, size2, first1 + 1, first2, arr, k);
    }
    else
    {
        arr[k] = arr2[first2];
        k++;
        sort(arr1, size1, arr2, size2, first1, first2 + 1, arr, k);
    }
}

void printArr(int arr[], int size)
{
    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << " ";
    }
    cout << endl;
}
