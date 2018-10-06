#include <iostream>
using namespace std;

void quickSort(char num[], int, int);
void enterString(char str[], int);

int main()
{
    cout << "Enter the length of your strings\n";
    int length = 0;
    cin >> length;

    char *str1 = new char[length] {};
    cout << "Enter the first string\n";
    enterString(str1, length);

    char *str2 = new char[length] {};
    cout << "Enter the second string\n";
    enterString(str2, length);

    quickSort(str1, 0, length - 1);
    quickSort(str2, 0, length - 1);

    bool isDifferent = false;
    for (int i = 0; i < length; i++)
    {
        if (str1[i] != str2[i])
        {
            isDifferent = true;
        }
    }

    delete[] str1;
    delete[] str2;

    if (isDifferent)
    {
        cout << "We can't get a string from another one";
    }
    else
    {
        cout << "We can get a string from another one";
    }

    return 0;
}

void enterString(char str[], int length)
{
    for (int i = 0; i < length; i++)
    {
        cin >> str[i];
    }
}

void quickSort(char num[], int first, int last)
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
