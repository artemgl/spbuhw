#include <iostream>
#include <time.h>
using namespace std;

bool isVarious(char arr[], int size);

int main()
{
    int const n = 4;
    char key[n] = {};

    srand(time(NULL));
    for (int i = 0; i < n; i++)
    {
        do
        {
            key[i] = rand() % 10;
        }
        while (!isVarious(key, i + 1));
    }

    printf("Try\tYour guess\n");

    int bull = 0;
    int cow = 0;
    int count = 0;
    char guess[n] = {};
    do
    {
        count++;
        printf("%2d.\t", count);

        for (int i = 0; i < n; i++)
        {
            cin >> guess[i];
            guess[i] -= '0';
        }

        if (isVarious(guess, n))
        {
            bull = 0;
            cow = 0;
            for (int i = 0; i < n; i++)
            {
                if (key[i] == guess[i])
                {
                    bull++;
                }

                for (int j = 0; j < n; j++)
                {
                    if (key[i] == guess[j])
                    {
                        cow++;
                    }
                }
            }
            cow -= bull;

            printf("\t\t%d bulls, %d cows\n", bull, cow);
        }
        else
        {
            printf("The numerals must be different\n");

            count--;
        }
    }
    while (bull < n);

    printf("You win!");

    return 0;
}

bool isVarious(char arr[], int size)
{
    for (int i = 0; i < size - 1; i++)
    {
        for (int j = i + 1; j < size; j++)
        {
            if (arr[i] == arr[j])
            {
                return false;
            }
        }
    }

    return true;
}
