#include <iostream>
using namespace std;

void printExpression(int arr[], int size);
void process(int summand[], int value, int k);

int sumWithoutOneLast(int arr[], int size);

int main()
{
    cout << "Enter value\n";
    int value = 0;
    cin >> value;

    int *summand = new int[value] {};

    process(summand, value, value);

    return 0;
}

void printExpression(int arr[], int size)
{
    for (int i = 0; i < size - 1; i++)
    {
        if (arr[i] != 0)
        {
            cout << arr[i] << " + ";
        }
    }

    cout << arr[size - 1] << "\n";
}

int sumWithoutOneLast(int arr[], int size)
{
    int result = 0;

    for (int i = 0; i < size - 1; i++)
    {
        result += arr[i];
    }

    return result;
}

void process(int summand[], int value, int k)
{
    if (k == 2)
    {
        if (summand[value - 2] == 0)
        {
            summand[value - 2]++;
        }

        summand[value - 1] = value - sumWithoutOneLast(summand, value);

        while (summand[value - 2] <= summand[value - 1])
        {
            printExpression(summand, value);

            summand[value - 2]++;
            summand[value - 1] = value - sumWithoutOneLast(summand, value);
        }
    }
    else
    {
        for (int i = 0; i < value; i++)
        {
            process(summand, value, k - 1);

            summand[value - k]++;

            for (int j = value - k + 1; j <= value - 2; j++)
            {
                summand[j] = summand[j - 1];
            }
        }
    }
}
