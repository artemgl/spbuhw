#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    const char fileName[] = "file.txt";

    ifstream fin(fileName);

    int rowsAmount = 0;
    fin >> rowsAmount;
    int columnsAmount = 0;
    fin >> columnsAmount;

    int **numbers = new int *[rowsAmount] {};
    for (int i = 0; i < rowsAmount; i++)
    {
        numbers[i] = new int[columnsAmount] {};
    }


    for (int i = 0; i < rowsAmount; i++)
    {
        for (int j = 0; j < columnsAmount; j++)
        {
            fin >> numbers[i][j];
        }
    }

    fin.close();

    bool **minForRows = new bool *[rowsAmount] {};
    for (int i = 0; i < rowsAmount; i++)
    {
        minForRows[i] = new bool[columnsAmount] {};
    }

    for (int i = 0; i < rowsAmount; i++)
    {
        int minimum = 0x7FFFFFFF;
        for (int j = 0; j < columnsAmount; j++)
        {
            if (minimum > numbers[i][j])
            {
                minimum = numbers[i][j];
            }
        }

        for (int j = 0; j < columnsAmount; j++)
        {
            if (minimum == numbers[i][j])
            {
                minForRows[i][j] = true;
            }
        }
    }

    bool **maxForColumns = new bool *[rowsAmount] {};
    for (int i = 0; i < rowsAmount; i++)
    {
        maxForColumns[i] = new bool[columnsAmount] {};
    }

    for (int i = 0; i < columnsAmount; i++)
    {
        int maximum = 0x80000000;
        for (int j = 0; j < rowsAmount; j++)
        {
            if (maximum < numbers[j][i])
            {
                maximum = numbers[j][i];
            }
        }

        for (int j = 0; j < rowsAmount; j++)
        {
            if (maximum == numbers[j][i])
            {
                maxForColumns[j][i] = true;
            }
        }
    }

    for (int i = 0; i < rowsAmount; i++)
    {
        for (int j = 0; j < columnsAmount; j++)
        {
            if (minForRows[i][j] && maxForColumns[i][j])
            {
                cout << numbers[i][j] << " (Coord: " << i << ' ' << j << ')' << endl;
            }
        }
    }

    for (int i = 0; i < rowsAmount; i++)
    {
        delete[] numbers[i];
    }
    delete[] numbers;

    for (int i = 0; i < rowsAmount; i++)
    {
        delete[] minForRows[i];
    }
    delete[] minForRows;

    for (int i = 0; i < rowsAmount; i++)
    {
        delete[] maxForColumns[i];
    }
    delete[] maxForColumns;

    return 0;
}
