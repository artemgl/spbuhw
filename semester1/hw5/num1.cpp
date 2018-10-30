#include <iostream>
#include <time.h>
using namespace std;

void movePosition(int &firstCoord, int &secondCoord, char direction);
void changeDirection(char &direction);
void printArray(int **array, int size);

bool isEnd(int firstCoord, int secondCoord, int size);

int main()
{
    int n = 0;
    cout << "Enter the dimension of the 2-dimensional array" << endl;
    cin >> n;

    int **arr = new int*[n];

    for (int i = 0; i < n; i++)
    {
      arr[i] = new int [n];
    }

    srand(time(NULL));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            arr[i][j] = rand() % 90 + 10;
        }
    }

    printArray(arr, n);

    int firstCoord = n / 2;
    int secondCoord = n / 2;

    cout << "Displaying array spirally:" << endl;
    cout << arr[firstCoord][secondCoord] << " ";

    int countOfSteps = 0;
    int k = 0;
    char direction = 'U';
    while (!isEnd(firstCoord, secondCoord, n))
    {
        changeDirection(direction);

        countOfSteps = (k++ + 2) / 2;

        for (int i = 0; i < countOfSteps && !isEnd(firstCoord, secondCoord, n); i++)
        {
            movePosition(firstCoord, secondCoord, direction);
            cout << arr[firstCoord][secondCoord] << " ";
        }
    }

    for (int i = 0; i < n; i++)
    {
      delete[] arr[i];
    }

    delete[] arr;

    return 0;
}

void movePosition(int &firstCoord, int &secondCoord, char direction)
{
    switch (direction)
    {
        case 'R':
        {
            firstCoord++;
            break;
        }
        case 'D':
        {
            secondCoord++;
            break;
        }
        case 'L':
        {
            firstCoord--;
            break;
        }
        case 'U':
        {
            secondCoord--;
            break;
        }
    }
}

void changeDirection(char &direction)
{
    switch (direction)
    {
        case 'R':
        {
            direction = 'D';
            break;
        }
        case 'D':
        {
            direction = 'L';
            break;
        }
        case 'L':
        {
            direction = 'U';
            break;
        }
        case 'U':
        {
            direction = 'R';
            break;
        }
    }
}

bool isEnd(int firstCoord, int secondCoord, int size)
{
    return (firstCoord == size - 1) && (secondCoord == 0);
}

void printArray(int **array, int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            cout << array[j][i] << " ";
        }
        cout << endl;
    }
    cout << endl;
}
