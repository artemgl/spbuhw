#include <iostream>
#include <time.h>
using namespace std;

void printArray(int arr[], int size);
void sort(int arr[], int size);

int main()
{
    int size = 0;
    cout << "Enter the size of the array\n";
    cin >> size;

    int *arrConsole = new int[size] {};

    for (int i = 0; i < size; i++)
    {
        cout << "Enter the " << i + 1 << " element" << endl;
        cin >> arrConsole[i];
    }

    srand(time(NULL));
    int *arrRandom = new int[size] {};

    for (int i = 0; i < size; i++)
    {
        arrRandom[i] = rand() % 32 + 11;
    }

    cout << "Before: " << endl;

    cout << "Random generated array: ";
    printArray(arrRandom, size);

    cout << "Your array: ";
    printArray(arrConsole, size);

    sort(arrRandom, size);
    sort(arrConsole, size);

    cout << "After: " << endl;

    cout << "Random generated array: ";
    printArray(arrRandom, size);

    cout << "Your array: ";
    printArray(arrConsole, size);

    return 0;
}

void printArray(int arr[], int size)
{
    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << " ";
    }
    cout << endl;
}

void sort(int arr[], int size)
{
    for (int i = 0; i < size; i += 2)
    {
        int j = i;
        while (arr[j] < arr[j - 2] && j >= 2)
        {
            swap(arr[j], arr[j - 2]);
            j -= 2;
        }
    }
}
