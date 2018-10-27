#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    char const fileName[] = {"file.txt"};
    ifstream f(fileName);

    int amount = 0;
    char currentSymbol = '\0';
    while (f.get(currentSymbol))
    {
        switch (currentSymbol)
        {
        case '\n':
        case ' ':
        case '\t':
        case '\r':
        {
            break;
        }
        default:
        {
            amount++;

            while (f.get(currentSymbol))
            {
                if (currentSymbol == '\n')
                {
                    break;
                }
            }
            break;
        }
        }
    }

    f.close();

    cout << "Amount of not empty strings is " << amount;

    return 0;
}
