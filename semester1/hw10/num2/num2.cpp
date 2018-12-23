#include <iostream>
#include <fstream>
using namespace std;

int length(char *string);
bool isEqual(char *firstString, char *secondString);

int main()
{
    const char fileName[] = "file.txt";
    const int maxLength = 256;
    const int modulo = 997;
    const int p = 3;

    ifstream fin(fileName);

    char *pattern = new char[maxLength] {};
    fin >> pattern;

    int patternLength = length(pattern);
    int patternHash = 0;
    for (int i = 0; i < patternLength; i++)
    {
        patternHash = (patternHash * p + pattern[i]) % modulo;
    }
    int usableP = 1;
    for (int i = 0; i < patternLength - 1; i++)
    {
        usableP = (usableP * p) % modulo;
    }

    fin.get();
    char *substring = new char[patternLength] {};
    for (int i = 0; i < patternLength; i++)
    {
        fin.get(substring[i]);
    }

    int substringHash = 0;
    for (int i = 0; i < patternLength; i++)
    {
        substringHash = (substringHash * p + substring[i]) % modulo;
    }
    substring[patternLength] = '\0';

    int index = 0;
    do
    {
        if (substringHash == patternHash && isEqual(pattern, substring))
        {
            cout << index << endl;
        }

        substringHash = (signed int)(substringHash - substring[0] * usableP);
        while (substringHash < 0)
        {
            substringHash += modulo;
        }

        for (int i = 0; i < patternLength - 1; i++)
        {
            swap(substring[i], substring[i + 1]);
        }
        fin.get(substring[patternLength - 1]);

        substringHash = (substringHash * p + substring[patternLength - 1]) % modulo;
        index++;
    }
    while (!fin.eof());

    fin.close();

    delete[] pattern;
    delete[] substring;

    return 0;
}

int length(char *string)
{
    int result = -1;
    while (string[++result] != '\0');
    return result;
}

bool isEqual(char *firstString, char *secondString)
{
    int lengthOfString = length(firstString);
    if (lengthOfString != length(secondString))
    {
        return false;
    }

    for (int i = 0; i < lengthOfString; i++)
    {
        if (firstString[i] != secondString[i])
        {
            return false;
        }
    }

    return true;
}
