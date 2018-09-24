#include <iostream>
using namespace std;

void sortarray(int num[], int a, int b);

int main()
{
    int const sizeOfArray = 15;

    int x[sizeOfArray] = {};
    for (int i = 0; i < sizeOfArray; i++)
    {
        x[i] = rand() % 100 + 1;
    }

    sortarray(x, 0, sizeOfArray - 1);

    for (int i = 0; i < sizeOfArray; i++)
    {
        cout << x[i] << ", ";
    }

    return 0;
}

void sortarray(int x[], int leftEnd, int rightEnd)
{
    if (leftEnd < rightEnd)
    {
        int middle = leftEnd - 1 + (rightEnd - leftEnd + 1) / 2;

        int i = leftEnd, j = rightEnd;
        while (i < middle && middle < j)
        {
            while (x[i] <= x[middle])
            {
                i++;
                if (i == middle)
                {
                    break;
                }
            }

            while (x[j] > x[middle])
            {
                j--;
            }

            if (i == middle || j == middle)
            {
                break;
            }

            swap(x[i], x[j]);
        }

        if (i == middle)
        {
            for (int k = middle + 1; k <= j; k++)
            {
                if (x[k] <= x[middle])
                {
                    if (k != middle + 1)
                    {
                        swap(x[k], x[middle + 1]);
                    }

                    swap(x[middle], x[middle + 1]);
                    middle++;
                }
            }
        }

        if (j == middle)
        {
            for (int k = middle - 1; k >= i; k--)
            {
                if (x[k] > x[middle])
                {
                    if (k != middle - 1)
                    {
                        swap(x[k], x[middle - 1]);
                    }

                    swap(x[middle], x[middle - 1]);
                    middle--;
                }
            }
        }

        sortarray(x, leftEnd, middle);
        sortarray(x, middle + 1, rightEnd);
    }
}
