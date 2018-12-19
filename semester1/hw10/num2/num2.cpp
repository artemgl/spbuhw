#include <iostream>
#include <fstream>
using namespace std;

int length(char *string);
bool isEqual(char *firstString, char *secondString);

int main()
{
    const char fileName[] = "file.txt";
    const int modulo = 10007;
    const int p = 7;
    const int maxLength = 256;

    cout << "Enter the pattern: ";
    char *pattern = new char[maxLength] {};
    gets(pattern);

    int patternLength = length(pattern);

    int patternHash = 0;
    for (int i = 0; i < patternLength; i++)
    {
        patternHash = ((patternHash * p) % modulo + pattern[i]) % modulo;
    }

    ifstream fin(fileName);

    int hash = 0;
    char *substring = new char[patternLength] {};
    for (int i = 0; i < patternLength; i++)
    {
        fin.get(substring[i]);
        hash = ((hash * p) % modulo + substring[i]) % modulo;
    }
    substring[patternLength] = '\0';

    int index = 0;
    do
    {
        if (hash == patternHash && isEqual(pattern, substring))
        {
            cout << index << endl;
        }

        for (int i = 0; i < patternLength - 1; i++)
        {
            swap(substring[i], substring[i + 1]);
        }

        fin.get(substring[patternLength - 1]);

        hash = 0;
        for (int i = 0; i < patternLength; i++)
        {
            hash = ((hash * p) % modulo + substring[i]) % modulo;
        }

        index++;
    }
    while (!fin.eof());

    fin.close();

    delete[] substring;
    delete[] pattern;

    return 0;
}

int length(char *string)
{
    int result = 0;
    while (string[result++] != '\0');

    return result - 1;
}

bool isEqual(char *firstString, char *secondString)
{
    if (length(firstString) != length(secondString))
    {
        return false;
    }

    for (int i = 0; i < length(firstString); i++)
    {
        if (firstString[i] != secondString[i])
        {
            return false;
        }
    }

    return true;
}
