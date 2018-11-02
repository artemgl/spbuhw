#include <iostream>
#include <fstream>
using namespace std;

int symbolIndex(char symbol);

char const firstSymbol = 'a';

int main()
{
    int const alphabetPower = 26;
    char const fileName[] = {"file.txt"};

    ifstream f(fileName);

    bool wasMet[alphabetPower] = {};
    while (!f.eof())
    {
        char currentSymbol = '\0';
        f.get(currentSymbol);

        if (isalpha(currentSymbol))
        {
            if (!wasMet[symbolIndex(currentSymbol)])
            {
                cout << currentSymbol;
                wasMet[symbolIndex(currentSymbol)] = true;
            }
        }
        else
        {
            bool isEmpty = true;
            for (int i = 0; i < alphabetPower; i++)
            {
                if (wasMet[i])
                {
                    isEmpty = false;
                    wasMet[i] = false;
                }
            }

            if (!isEmpty)
            {
                cout << ' ';
            }
        }
    }

    f.close();

    return 0;
}

int symbolIndex(char symbol)
{
    return tolower(symbol) - firstSymbol;
}
